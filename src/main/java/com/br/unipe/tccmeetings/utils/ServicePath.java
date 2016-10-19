package com.br.unipe.tccmeetings.utils;

public final class ServicePath {

	///////////////////////////////////////////////////////////////
	// ROOT PATH
	///////////////////////////////////////////////////////////////

	public static final String ALL = "/**";

	public static final String ROOT_PATH = "/api";

	public static final String PUBLIC_ROOT_PATH = ROOT_PATH + "/public";

	public static final String PRIVATE_ROOT_PATH = ROOT_PATH + "/private";

	///////////////////////////////////////////////////////////////
	// PRIVATE PATHS
	///////////////////////////////////////////////////////////////

	public static final String PERMISSION_PATH = PRIVATE_ROOT_PATH + "/permission";

	public static final String USER_PATH = PRIVATE_ROOT_PATH + "/user";

	public static final String DISCIPLINA_PATH = PRIVATE_ROOT_PATH + "/disciplina";

	public static final String CURSO_PATH = PRIVATE_ROOT_PATH + "/curso";

	public static final String REUNIAO_PATH = PRIVATE_ROOT_PATH + "/reuniao";

	public static final String DOCENTE_PATH = PRIVATE_ROOT_PATH + "/docente";

	public static final String DISCENTE_PATH = PRIVATE_ROOT_PATH + "/discente";

	///////////////////////////////////////////////////////////////
	// PUBLIC PATHS
	///////////////////////////////////////////////////////////////

	public static final String LOGIN_PATH = PUBLIC_ROOT_PATH + "/login";

	public static final String LOGOUT_PATH = PUBLIC_ROOT_PATH + "/logout";

}
