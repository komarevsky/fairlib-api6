/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.betfair;

import com.betfair.publicapi.types.exchange.v5.ArrayOfCancelBets;
import com.betfair.publicapi.types.exchange.v5.ArrayOfPlaceBets;
import com.betfair.publicapi.types.exchange.v5.CancelBetsResp;
import com.betfair.publicapi.types.exchange.v5.PlaceBetsResp;
import com.freebetbot.fairlib.util.HeaderChecker;
import com.freebetbot.xlogger.XLogger;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class BetPlacementAPI {
    
    public static PlaceBetsResp placeBets(ArrayOfPlaceBets bets) {
        PlaceBetsResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.placeBets(bets);
            if (HeaderChecker.isPlaceBetsResponseOk(result)) {
                SessionManager.setExRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            XLogger.sendSevere(ex);
            SessionManager.restartSession();
        }
        
        return result;
    }

    public static CancelBetsResp cancelBets(ArrayOfCancelBets bets) {
        CancelBetsResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.cancelBets(bets);
            if (HeaderChecker.isCancelBetsResponseOk(result)) {
                SessionManager.setExRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            XLogger.sendSevere(ex);
            SessionManager.restartSession();
        }
        
        return result;
    }
}
