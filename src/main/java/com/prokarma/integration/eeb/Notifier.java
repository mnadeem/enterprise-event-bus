package com.prokarma.integration.eeb;

import java.util.concurrent.Future;

public interface Notifier {

	Future<String> notifySubscribers(String topic);

}
