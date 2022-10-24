package nicolis_A_MTP.bin.providers;

import nicolis_A_MTP.bin.Utility;
import nicolis_A_MTP.bin.packages.MTPPacket;
import nicolis_A_MTP.bin.packages.registration.MTPRegistrationError;
import nicolis_A_MTP.bin.packages.registration.MTPRegistrationRequest;
import nicolis_A_MTP.bin.packages.registration.MTPRegistrationSuccess;


public class RegistrationProviders {


    public static MTPPacket evaluateRequest(MTPRegistrationRequest registrationRequest){
        if (registrationRequest.getName().equals("deMartini")){
            return new MTPRegistrationSuccess(Utility.nextNonNegative());
        }
        return new MTPRegistrationError();
    }

}
