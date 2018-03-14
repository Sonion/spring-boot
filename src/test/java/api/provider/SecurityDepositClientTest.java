package api.provider;

import api.ServiceRunner;
import api.client.SecurityDepositClient;
import api.dto.SecurityDepositDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by wujinliang on 2018/3/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceRunner.class)
public class SecurityDepositClientTest {

    @Test
    public void loadCreditById() throws Exception{
        System.out.println(SecurityDepositClient.loadCreditById(1));
    }

    @Test
    public void updateCredit() throws Exception{
        SecurityDepositDTO securityDepositDTO =
                new SecurityDepositDTO().platformId(0).credit(9999).
                        platformTag(0).tagNum(0);
        SecurityDepositClient.updateCredit(securityDepositDTO);
    }

}
