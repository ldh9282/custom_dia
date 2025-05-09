package com.custom.dia.cmmn.constant;

/**
 * <pre>
 * 클래스명: URLConstant
 * 설명: URL 매핑용 클래스
 * </pre>
 */
public class URLConstant {

	/**
	 * <pre>
	 * 변수명: STATIC_RESOURCE_PATH
	 * 설명: 정적 파일 경로
	 * </pre>
	 */
	public static final String STATIC_RESOURCE_PATH = "/resources/**";
	/**
	 * <pre>
	 * 변수명: LOGIN_PAGE_URL
	 * 설명: 로그인 페이지 URL
	 * </pre>
	 */
	public static final String LOGIN_PAGE_URL = "/DIALG01";
	/**
	 * <pre>
	 * 변수명: LOGIN_PAGE_URL2
	 * 설명: 로그인 페이지 URL2
	 * </pre>
	 */
	public static final String LOGIN_PAGE_URL2 = "/DIALG04";
	/**
	 * <pre>
	 * 변수명: LOGIN_PROC_URL
	 * 설명: 로그인 POST URL
	 * </pre>
	 */
	public static final String LOGIN_PROC_URL = "/login";
	/**
	 * <pre>
	 * 변수명: LOGIN_PROC_URL2
	 * 설명: 로그인 POST URL2
	 * </pre>
	 */
	public static final String LOGIN_PROC_URL2 = "/DIALG05";
	/**
	 * <pre>
	 * 변수명: LOGIN_SUCC_URL
	 * 설명: 로그인 성공시 리다이렉트 URL
	 * </pre>
	 */
    public static final String LOGIN_SUCC_URL = "/";
    /**
	 * <pre>
	 * 변수명: LOGOUT_PROC_URL
	 * 설명: 로그아웃 POST URL
	 * </pre>
	 */
	public static final String LOGOUT_PROC_URL = "/logout";
	/**
	 * <pre>
	 * 변수명: LOGOUT_SUCC_URL
	 * 설명: 로그아웃 성공시 리다이렉트 URL
	 * </pre>
	 */
	public static final String LOGOUT_SUCC_URL = LOGIN_PAGE_URL2;
	/**
	 * <pre>
	 * 변수명: REGISTER_PAGE_URL
	 * 설명: 회원가입 페이지 URL
	 * </pre>
	 */
	public static final String REGISTER_PAGE_URL = "/DIALG02";
	/**
	 * <pre>
	 * 변수명: REGISTER_PROC_URL
	 * 설명: 회원가입 POST URL
	 * </pre>
	 */
	public static final String REGISTER_PROC_URL = "/DIALG03";
}
