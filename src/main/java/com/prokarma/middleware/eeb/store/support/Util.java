package com.prokarma.middleware.eeb.store.support;

import java.util.UUID;

public final class Util {

	private Util() {

	}

	public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
