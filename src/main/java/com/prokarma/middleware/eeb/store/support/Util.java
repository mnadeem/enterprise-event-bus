package com.prokarma.middleware.eeb.store.support;

import java.util.UUID;

public class Util {

	private Util() {

	}

	public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
