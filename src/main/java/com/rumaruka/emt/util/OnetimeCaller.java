package com.rumaruka.emt.util;

public class OnetimeCaller {
    public Runnable call;

    public OnetimeCaller(Runnable run)
    {
        call = run;
    }

    public void call()
    {
        if(call != null)
            call.run();
        call = null;
    }
}
