package kb.zango.domain.transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import io.codef.api.EasyCodefUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TransactionService {

    @Value("${codef.client-id}")
    private String clientId;

    @Value("${codef.client-secret}")
    private String clientSecret;

    @Value("${codef.public-key}")
    private String publicKey;

    private EasyCodef codef = new EasyCodef();

    @PostConstruct
    public void publishToken() throws IOException {

    }

    public String register() throws IOException, InterruptedException {
        codef.setClientInfoForDemo(clientId, clientSecret);
        codef.setPublicKey(publicKey);
        String accessToken1 = codef.requestToken(EasyCodefServiceType.DEMO);	// 토큰 요청1 (requestToken)
        System.out.println("액세스토큰:" + accessToken1);
        List<HashMap<String, Object>> accountList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> accountMap = new HashMap<String, Object>();
        accountMap.put("countryCode",	"KR");
        accountMap.put("businessType",	"BK");
        accountMap.put("clientType", 	"P");
        accountMap.put("organization",	"0004"); // 기관코드는 각 상품 페이지에서 확인 가능
        accountMap.put("loginType",  	"1");
        accountMap.put("id",  		"wjdgus980812");

        try {
            accountMap.put("password",  EasyCodefUtil.encryptRSA("9972486wg@", codef.getPublicKey())); // RSA암호화가 필요한 필드는 encryptRSA(String plainText, String publicKey) 메서드를 이용해 암호화
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        accountList.add(accountMap);

        HashMap<String, Object> parameterMap = new HashMap<String, Object> ();
        parameterMap.put("accountList", accountList);

/** #6.계정 등록 요청(Connected ID 발급 요청)	- 서비스타입(API:정식, DEMO:데모, SANDBOX:샌드박스) */
        String result = codef.createAccount(EasyCodefServiceType.DEMO, parameterMap);

/** #7.결과 확인	*/
        System.out.println(result);
        return result;
    }
}
