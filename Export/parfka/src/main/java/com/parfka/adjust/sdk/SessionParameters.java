package com.parfka.adjust.sdk;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pfms on 29/07/2016.
 */
public class SessionParameters {
    Map<String, Object> callbackParameters;
    Map<String, Object> partnerParameters;

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        SessionParameters otherSessionParameters = (SessionParameters) other;

        if (!Util.equalObject(callbackParameters, otherSessionParameters.callbackParameters)) return false;
        if (!Util.equalObject(partnerParameters, otherSessionParameters.partnerParameters)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode = 37 * hashCode + Util.hashObject(callbackParameters);
        hashCode = 37 * hashCode + Util.hashObject(partnerParameters);
        return hashCode;
    }

    public SessionParameters deepCopy() {
        SessionParameters newSessionParameters = new SessionParameters();
        if (this.callbackParameters != null) {
            newSessionParameters.callbackParameters = new HashMap<String, Object>(this.callbackParameters);
        }
        if (this.partnerParameters != null) {
            newSessionParameters.partnerParameters = new HashMap<String, Object>(this.partnerParameters);
        }
        return newSessionParameters;
    }
}
