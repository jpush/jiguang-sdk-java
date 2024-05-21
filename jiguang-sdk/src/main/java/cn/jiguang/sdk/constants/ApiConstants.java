package cn.jiguang.sdk.constants;

public interface ApiConstants {

    interface Audience {
        String ALL = "all";
    }

    interface Platform {
        String ALL = "all";
        String ANDROID = "android";
        String IOS = "ios";
        String HMOS = "hmos";
        String QUICK_APP = "quickapp";
    }

    interface MessageType {
        int NOTIFICATION = 1;
        int CUSTOM = 2;
        int LIVE_ACTIVITY = 16;
    }

    interface MessageStatus {
        int RECEIVED = 0;
        int NOT_RECEIVED = 1;
        int REGISTRATION_ID_DOES_NOT_BELONG_TO_THIS_APPLICATION = 2;
        int REGISTRATION_ID_IS_NOT_THE_PUSH_TARGET_OF_THIS_MESSAGE = 3;
        int SYSTEM_ERROR = 4;
    }

    interface ImageType {
        int BIG_IMAGE = 1;
        int BIG_ICON = 2;
        int SMALL_ICON = 3;
    }

}
