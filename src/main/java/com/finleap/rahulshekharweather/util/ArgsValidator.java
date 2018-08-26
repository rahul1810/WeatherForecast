package com.finleap.rahulshekharweather.util;

import com.finleap.rahulshekharweather.exception.ApplicationException;
import com.finleap.rahulshekharweather.response.ResponseInfo;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Component
public class ArgsValidator {

    public HashSet<String> validate(String[] validArgumentArray, Set<String> inputArgs) throws ApplicationException {
        Set<String> validArgs = new HashSet<>();
        Collections.addAll(validArgs, validArgumentArray);
        if(validArgs.containsAll(inputArgs)){
            return null;
        }
        else {
            throw new ApplicationException(ResponseInfo.UNSUPPORTED_QUERY_PARAMETERS);
        }

    }
}
