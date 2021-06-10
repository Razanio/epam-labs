package com.epam.courses.testdao;

import com.epam.courses.testdao.test.AccountDaoTest;
import com.epam.courses.testdao.test.AccountsAddonsDaoTest;
import com.epam.courses.testdao.test.AddonDaoTest;
import com.epam.courses.testdao.test.UserDaoTest;

public class MainDaoTest {
    public static void main(String[] args) {
        UserDaoTest userDaoTest = new UserDaoTest();
        AddonDaoTest serviceDaoTest = new AddonDaoTest();
        AccountDaoTest accountDaoTest = new AccountDaoTest();
        AccountsAddonsDaoTest accountsServicesDaoTest = new AccountsAddonsDaoTest();

        accountsServicesDaoTest.test();
        System.out.println("\nAccountsServicesDaoTest has been completed\n");
        accountDaoTest.test();
        System.out.println("\nAccountDaoTest has been completed\n");
        serviceDaoTest.test();
        System.out.println("\nServiceDaoTest has been completed\n");
        userDaoTest.test();
        System.out.println("\nUserDaoTest has been completed\n");

    }
}
