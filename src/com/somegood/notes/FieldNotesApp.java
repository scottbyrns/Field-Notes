package com.somegood.notes;

import android.app.Application;

import com.kinvey.KCSClient;
import com.kinvey.KinveySettings;
import com.somegood.notes.data.source.BaseKinveyDataSource;

/**
 * Store global state. In this case, the single instance of KCS.
 */
public class FieldNotesApp extends Application {

    private KCSClient service;

	// Enter your Kinvey app credentials
	private static final String APP_KEY = "kid2111";
	private static final String APP_SECRET = "7e219a50901f4e638f69ddcef5589e79";
    
    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    private void initialize() {
		// Enter your app credentials here
		service = KCSClient.getInstance(this.getApplicationContext(), new KinveySettings(APP_KEY, APP_SECRET));

        BaseKinveyDataSource.setKinveyClient(service);
    }

    public KCSClient getKinveyService() {
        return service;
    }

}
