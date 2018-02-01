/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import services.accountService.AccountService;

/**
 *
 * @author Ivan Naumov
 */
public class AccountServiceController implements AccountServiceControllerMBean {

    private final AccountService accountService;
    
    public AccountServiceController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @Override
    public int getSessoinCount() {
        return accountService.getSessionCount();
    }

    @Override
    public int getSessionsLimit() {
        return accountService.getSessionsLimit();
    }

    @Override
    public void setSessionsLimit(int limit) {
        accountService.setSessionsLimit(limit);
    }
}
