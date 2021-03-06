package com.freebetbot.fairlib.betfair;

import com.betfair.publicapi.types.exchange.v5.GetAccountFundsResp;
import com.betfair.publicapi.types.global.v3.ViewProfileResp;
import com.freebetbot.fairlib.util.HeaderChecker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class AccountManagementAPI {
    
    private static final Log LOGGER = LogFactory.getLog(AccountManagementAPI.class);
    
    public static GetAccountFundsResp getAccountFunds() {
        GetAccountFundsResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.getAccountFunds();
            if (HeaderChecker.isGetAccountFundsResponseOk(result)) {
                SessionManager.setExRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            LOGGER.error(ex.getMessage(), ex);
            SessionManager.restartSession();
        }
        
        return result;
    }
    
    public static ViewProfileResp viewProfile() {
        ViewProfileResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.viewProfile();
            if (HeaderChecker.isViewProfileResponseOk(result)) {
                SessionManager.setRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            LOGGER.error(ex.getMessage(), ex);
            SessionManager.restartSession();
        }
        
        return result;
    }

}
