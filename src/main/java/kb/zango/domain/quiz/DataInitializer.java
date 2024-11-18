package kb.zango.domain.quiz;

import kb.zango.domain.quiz.entity.Choice;
import kb.zango.domain.quiz.entity.MultipleChoiceQuiz;
import kb.zango.domain.quiz.entity.OXQuiz;
import kb.zango.domain.quiz.entity.QuizGroup;
import kb.zango.domain.quiz.repository.QuizGroupRepository;
import kb.zango.domain.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

//@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final QuizGroupRepository quizGroupRepository;
    private final QuizRepository quizRepository;

    @Override
    public void run(String... args) throws Exception {
        // First QuizGroup with 2 OX Quizzes
        QuizGroup quizGroup1 = new QuizGroup("Quiz Group 1", "Description 1", LocalDate.now().plusDays(30), "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxASDxUSEA8VEhIQDxAQFRUQDw8PEA8PFRUWFhURFRUYHSggGBolHRUVITEhJSkrLi4uFx8zODMvNygtLisBCgoKDg0OFRAQFysdFR0rLS0tLSsrLS0rLS0rLSsrLS0rLS0rLS0tLS0rLS0rKy0tLS0rLSstLSs3Ny0tNy0rK//AABEIAKgBLAMBIgACEQEDEQH/xAAbAAACAgMBAAAAAAAAAAAAAAAAAQIHAwQFBv/EAEIQAAIBAgIFCAcGBAUFAAAAAAABAgMRBBIFITFBUQYyYXFykbGyEyIzQnOBoQcjUmKCwUPR4fAUJFPC8RUWJUSS/8QAGQEAAwEBAQAAAAAAAAAAAAAAAAEEAgUD/8QAIhEBAAIDAQACAgMBAAAAAAAAAAECBBExAyEyEkEFE3FR/9oADAMBAAIRAxEAPwCjQAAAAAAAtLB3VOPYj4FWlq4Tmx7MfAlyuQ6P8f8AazMlqJIBpEm3TF9YrX/veTsKD1jCNiOUyd4so2WJxEzK0Y2AQaFYm0KwghYMoqtSMedJLraRrS0pQTs60e+4an/hTaI/bZsFjXhpGjLm1YP9SRsxdwmJgRMSVgykrAhGjYdhgwCIDsOwBGwJE0hWAFYkKw2h7A2hbYNAkBNXSPsanwp+VlZFnaR9hU1fwp+VlYlmPyXMzvtUAAFCEAAAAAAAAAAAItbCL1Y9mPgVUi2MJG0I9mPgS5XIdH+P7ZmS1DiDerYS2biN00SLMkiDGEbiuNiAFci3YlJI8lp3TTm3Cm7QWptbZ/0N0pNpeXp6RSNy6mkNPUqeqPry6H6q+Z53F6brz9/KuENX12nPbOho/QWKr+yoTkuNssf/AKdkVV861c+/ve/OOfKbb1tvrdxHttHfZziJa69WNJcI/eS/kjvYT7OcJH2k6lR9MlBL5RRr+ysPL8ZnqqrGfDY2pTd4VHHovq7i4qHI7AR/9aLt+PNN/Vm9Q0JhYczDUl1U4fyMT6RP6aiJjkqx0Zp2c9VSlJ/npwlJfNI7lOSaut57yM6aeVSimtycU18jjaf0YknWgrNa5pb1+PrR4XrE8WeXvPLPPATyg4nksQBEso7CJELErDsMkbBYyEnEC2xRiSaJ28AsMmnpP2FT4VTysq4tTSa+4qfCqeVlVlmPyXNzftUAAFCEAAAAAAAAAAA0W3h+ZHsx8CpEW1hubHsrwJcnkOh/H9szWJSINmTcRuogxWJsiMINCMjISYE4HKfSDjH0UedJa7bVHh8zr6H+zum4RliasnJpNwp2io33OW1/Q8lRl6fHw3qeIpr9OZau5Fn8sNJOhg5yg7Tm1Ti1uctr7rlMR+OohzfW/wCVpl5bSmk8BgZunhcJTqVYanOp66jLhd62+4xUOX+MVpSw0JU/ywqRXylrRtchuS1OrD/E4iObNK9OMn6rS9+S33ZYUaSStZW4WVu4czH+vPTj8nuUNHFwvD1ZRtmhLnR/muk7B52ryXVPFwxOFap+tarDZGcHzmuD6D0SRmTDPAaf07icTXeFwN7K6lKLs5bm83ux8T31am5RaTs3Fq/C62nK5PaBhhKeVPNOTvObVnN/sgiQ8TV+zvEZc6xEHU22tNa+3f62OhyL0tXVaWCxWaUoxllc9ckltg3vVtjPe2MboQzZ8qzWtmss1uFxzbfRp4rFYf0dWdPdFpx7Eta7ta+RisdrlNStUpy/FGUH1q0l/uOQ0eFo+XR8rbrCKQWJJAkZehJEkgRJMbMhRHYSBgQYkiQgJq6UX+XqfCqeVlVFq6U9hU+FU8rKqLMfkubm9qAAChEAAAAAAAAAAAaLbw3MXZj4FSItvDc1dleBLk8h0MDtmVE1+/0IpdBkiiN00BMm0QYwTNDTFXLQnJfhaXW9Rv2ORyn1YZ9Mo+JqsbmGfSdVlwOR6vpDD3/1b90ZMuLH6NpYin6OtHNC8ZWu1rWzYVByLj/5Gh25eSRdUXq+RT6dcyOFRoxjFRirRikklqSS2JGSxz/Q1auuc3Si9kKbtO3557upd5L/AKVS/Pfj6arfvuYNuisc6U3CWWFeM5f6dWcc76pLX3pm5h66nG6VtzT1OLW1MQZSFarGKcpSUUt7dkOpOybs3ZN2WtvoR5fG8ocJSqr/ABNTNUT1QhFzhh+u3vcWMncWkM3Mo1JrjljBPqztNmSGKu0pQlBv8SVn0XV0TwuJhUgpwkpRmk01saMrA3J5R0c1HMv4c4z+WtS+jZ5to9vNJqzV01Y8tpTRzovNHXSb1cad/dfR0mLQo8PSI+JaBKwAzzVbDC4mFwI7DI7hpgDQ2t9/+SCZKMl/a2DZlq6Vj9xU+DU8rKqLW0s16CrZ/wAKp1c1lUlePyXOze1AABQiAAAAAAAAAAANbS3MMvVXZXgVHHaW9hY+qupeBLk8h0MDtmZEwSB/0JHTJfVXINk1dO9ugg9owjY28DoSOJi/Sr7pS2bHUafHcrmnVvZ222duvce0wlFQhGC2Rio9xqsfLw976jThf9o4eGJo16EFTdOTzRV8souMldcHdo9GkSFc9UIRo6bqzjh6sqfPjSm4223sb1xNAFL6LhhJxrTxeIqQqxjmpZE25z1u7duNuBZ3JavOeDozqNuUqabb2vg38rDqclcC6npHhouTd9+W/HLsOtGmkkkrJKySVkkatOxEI1NjtwKjpV8LSjiqeMoSliJNqnLfTnr163q12d9dy37GGrhKcpKUqcJNbHKEW182KJ0Jjbzf2f0ascElUTSc5ygmmnkdt3Xc9ONoQgTMdSKaaaumrNPY0TYhh5XHYZ0qmTbFrNBvbbfH5au81rHf5QU70c2+nJS+WyX0f0PPOTPK0LfK26pibIuTI5ukzp6MiQRSIOT4/QPSMAnlHGxBTIARaUt6CrZfwanlZUpamk6n3FT4VTysqsrx+S5uZ2AAAUIwAAAAAAAAAADjtXWXDhl6q7K8Cn4bV1ouGi/VXUvAmyeQ6GB2zKw3DSESOkVyMibQmgNjk7K72JpvqTTZ7eDPFWvtO/oXHpwVOT9eCtrds0VsaN1TZFdxt2BBcLnokAxIYAmJjEwBAAMARFjbEMIsQ2RbAnP07L/L1OmKXe0v3PONHY5RV9UKa2ylnfRGOz627jjs87K/CPjaAWGFzL2RaBokJsBshMYhE1dJ+wqfCqeVlXlo6T9hU+FPysq4rx+S52b9qgAAoRAAAAAAAAAAAJQ2rrRcNBal1Ip2G1daLmoc1dleBNk8hfgdsnFErAkSSJHS2hYi0ZJIw4ipli5Wbsti1t9Axs1HYKUdbW0VCo3ZtWbV2uBkbT2bgAwto1qcnsVVX6LpxX1aPXpngMfjlG8Esztr4L+p0dCcqbJQxO7UqiTd+2v3N1l4e3lM/MQ9fcLmKjXjNXhJST3xaaMhtKYguAEwYrFQpxzVJKMeL48FxNHB6foVZ5ItqT2ZouKl1M5nK3CVZzhKEJTiotWir5ZX226vA5mitE15VYN05QjCcZuU1l5rvZLexvSKxre3uLkWJCnJJXbslvepIHmDWx2LhSg5TfUt8nuS6TRxWnaa1UvvGtV0/UT7W/5HDxFWU5ZpyzPd+GK4RW4zNnpTymf8OrXlOTnPnS4e7HdH5GNsQGJWRERGjuIAEJAXGRAg2K4xAGtpP2FT4U/Kyryz9J+wqfDqeVlYFWPyXOzftAAAKEQAAAAAAAAAAALlw79VdleBTRcuH5q7K8CbJ5C/A7ZsRvcxU6E/SSnm9VwSjFt2T3tmWLMilwRLDoShSUsq9JbNZXy3aFKxJyMUpDMSNXG4pU4t79y4yM7Z53EVc83Lde0eiKBqsblDr2/uMBGlOmTD1503enOUH+VtX61vOzhOVWIhz1Gqun1Jd61fQ4YA87eVLdh6uPLJb8PL5TgzNHlfQ306q/TB+DPHCuPbynFo9fU5Y0vdo1H15I/ualXljP3cPH9VR/tE83cVw2IxaO1W5VYp81U4dUXJ/VnKxeOrVfa1JS6G7R7lqMNxNi23HjSOQIVJQd4anw3SXBo7FGqpRUlvV+p8DjXNjRtS03HdJX/Uv6eAherqiABPIAwACAmwACDYrhcTAMGk39xU+FU8rKvLO0l7Cp8Kp5WViVY/Jc7N+0AAAoRAAAAAAAAAAAGi4cO/VXZXgU6i38M/Uj2Y+BNk8hfgds2VIzZjBEyRkSOlJzMTZObMLYyhr6Qq5aUnf3Wl1vUjiRVkb2mJa4R7Uu7UvE0TUPbzgwEJsb1O47kUwAjuIVwuBncQgAHcTAQiRi9q4MHKzUl7sk/lv+lyF/X61clPY+obM/MO+mBgwTbpxv8Agj4GcynkNgJmKdR5lHK3dNt7lYCZWK5jhUTbSd3F2fQ+AZ9drPZe+7qAMlxNkRiLTW0l7Cp8KflZWZZmkX9xU+FPysrMrx+S52b9oAABQiAAAAAAAAAAANFvYTmLsx8CoS3cJzI9mPgTZPIX4H2s2YkiNgRI6QkzFcyyMbQxDj6Vf3kVwg/q1/I1De0vTtKMt8k492u5oGoe1OHcx1pWXXqXWTMMneS/Ld/MbUyyxWokJABhiGAgQMAYGQhiYExS567L8SVTY+pkKnOj+olPXq4tLvdhsO3heZHsR8DK2NR1A0YeBXE0OwrASKilsW3X1sYxMZC4AFhBq6S9hU+FPysrMs3Sfsanwp+VlZFePyXOzftAAAKEQAAAAAAAAAABluYTmR7MfAYE2TyF+D2zZTGwAkdEmhNAA2WhpWg5RTW2DvbjfVZdJy1RnfLklm4ZJX7rABqB/bNOMeJhOGqVOSfBwlHxIUoWSX9tgAS35+s26mAAJ7RY7EXJcQAZWvMEpLiiVnwfcwAW3jPvMfpFtcRXQANuvrMsmDwE69VRp2uoyk3K6SWpbl0nRnoSpRnCU5RavJrLd60t910gA/08Letvz/H9Nq5GTEBltG4nIYCMsw0wAZSdxoAEy1dKr7mp8OflZWIAV4/Jc/M7AAAKEQAAAP/Z", 0L, 100L);
        quizGroupRepository.save(quizGroup1);

        OXQuiz oxQuiz1 = new OXQuiz(quizGroup1, "OX Quiz 1", 1);
        OXQuiz oxQuiz2 = new OXQuiz(quizGroup1, "OX Quiz 2", 0);
        quizRepository.saveAll(Arrays.asList(oxQuiz1, oxQuiz2));

//        Choice choice1 = new Choice("True", true, oxQuiz1);
//        Choice choice2 = new Choice("False", false, oxQuiz1);
//        Choice choice3 = new Choice("True", true, oxQuiz2);
//        Choice choice4 = new Choice("False", false, oxQuiz2);
//
//        // Second QuizGroup with 2 MultipleChoice Quizzes
//        QuizGroup quizGroup2 = new QuizGroup("Quiz Group 2", "Description 2", LocalDate.now().plusDays(30), "imageUrl2", 0L, 200L);
//        quizGroupRepository.save(quizGroup2);
//
//        MultipleChoiceQuiz mcQuiz1 = new MultipleChoiceQuiz(quizGroup2, "MC Quiz 1", 1);
//        MultipleChoiceQuiz mcQuiz2 = new MultipleChoiceQuiz(quizGroup2, "MC Quiz 2", 2);
//        quizRepository.saveAll(Arrays.asList(mcQuiz1, mcQuiz2));
//
//        Choice choice5 = new Choice("Option 1", false, mcQuiz1);
//        Choice choice6 = new Choice("Option 2", true, mcQuiz1);
//        Choice choice7 = new Choice("Option 3", false, mcQuiz1);
//        Choice choice8 = new Choice("Option 4", false, mcQuiz1);
//        Choice choice9 = new Choice("Option 1", false, mcQuiz2);
//        Choice choice10 = new Choice("Option 2", false, mcQuiz2);
//        Choice choice11 = new Choice("Option 3", true, mcQuiz2);
//        Choice choice12 = new Choice("Option 4", false, mcQuiz2);
//        choiceRepository.saveAll(Arrays.asList(choice5, choice6, choice7, choice8, choice9, choice10, choice11, choice12));
//
//        // Third QuizGroup with 1 OX Quiz and 1 MultipleChoice Quiz
//        QuizGroup quizGroup3 = new QuizGroup("Quiz Group 3", "Description 3", LocalDate.now().plusDays(30), "imageUrl3", 0L, 300L);
//        quizGroupRepository.save(quizGroup3);
//
//        OXQuiz oxQuiz3 = new OXQuiz(quizGroup3, "OX Quiz 3", 1);
//        MultipleChoiceQuiz mcQuiz3 = new MultipleChoiceQuiz(quizGroup3, "MC Quiz 3", 3);
//        quizRepository.saveAll(Arrays.asList(oxQuiz3, mcQuiz3));
//
//        Choice choice13 = new Choice("True", true, oxQuiz3);
//        Choice choice14 = new Choice("False", false, oxQuiz3);
//        Choice choice15 = new Choice("Option 1", false, mcQuiz3);
//        Choice choice16 = new Choice("Option 2", false, mcQuiz3);
//        Choice choice17 = new Choice("Option 3", false, mcQuiz3);
//        Choice choice18 = new Choice("Option 4", true, mcQuiz3);
//        choiceRepository.saveAll(Arrays.asList(choice13, choice14, choice15, choice16, choice17, choice18));
    }
}