package kb.zango.domain.transaction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import io.codef.api.EasyCodefUtil;
import kb.zango.domain.transaction.dto.CodefApiRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CodefTransactionService {

    @Value("${codef.client-id}")
    private String clientId;

    @Value("${codef.client-secret}")
    private String clientSecret;

    @Value("${codef.public-key}")
    private String publicKey;


    private EasyCodef codef = new EasyCodef();

    public String register(CodefApiRequestDTO codefApiRequestDTO) throws IOException, InterruptedException {
        codef.setClientInfoForDemo(clientId, clientSecret);
        codef.setPublicKey(publicKey);

        List<HashMap<String, Object>> accountList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> accountMap = new HashMap<String, Object>();
        accountMap.put("countryCode",	"KR");
        accountMap.put("businessType",	"BK");
        accountMap.put("clientType", 	"P");
        accountMap.put("organization",	"0004"); // 기관코드는 각 상품 페이지에서 확인 가능
        accountMap.put("loginType",  	"1");
        accountMap.put("id",  		codefApiRequestDTO.getUserId());

        try {
            accountMap.put("password",  EasyCodefUtil.encryptRSA(codefApiRequestDTO.getUserPassword(), codef.getPublicKey())); // RSA암호화가 필요한 필드는 encryptRSA(String plainText, String publicKey) 메서드를 이용해 암호화
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        accountList.add(accountMap);

        HashMap<String, Object> parameterMap = new HashMap<String, Object> ();
        parameterMap.put("accountList", accountList);

/** #6.계정 등록 요청(Connected ID 발급 요청)	- 서비스타입(API:정식, DEMO:데모, SANDBOX:샌드박스) */
        String connectedIdResponse = codef.createAccount(EasyCodefServiceType.DEMO, parameterMap);
        String connectedId = extractConnectedIdFromResponse(connectedIdResponse);

/** #7.결과 확인	*/
//        System.out.println(connectedIdResponse);

        /** #5.요청 파라미터 설정 - 각 상품별 파라미터를 설정(https://developer.codef.io/products) */

        // 한달 기간 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String startDate = LocalDateTime.now().minusMonths(1).format(formatter);
        String endDate = LocalDateTime.now().format(formatter);

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("connectedId", connectedId);
        param.put("organization", "0004");
        param.put("account", codefApiRequestDTO.getUserAccountNum());
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("orderBy", "0");

/** #6.코드에프 정보 조회 요청 - 서비스타입(API:정식, DEMO:데모, SANDBOX:샌드박스) */
        String productUrl = "/v1/kr/bank/p/account/transaction-list";	// 수시입출 거래내역 url
        String result = codef.requestProduct(productUrl, EasyCodefServiceType.DEMO, param);


/**	#7.코드에프 정보 결과 확인	*/
        System.out.println(result);

        return result;
    }

    private String extractConnectedIdFromResponse(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = (ObjectNode) mapper.readTree(response);

        // "data" 객체 안에 있는 "connectedId" 필드 추출
        if (rootNode.has("data") && rootNode.get("data").has("connectedId")) {
            return rootNode.get("data").get("connectedId").asText();
        } else {
            throw new IOException("connectedId not found in response");
        }
    }
}
