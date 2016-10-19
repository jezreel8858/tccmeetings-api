package com.br.unipe.tccmeetings.exception;

public class ServerException extends GenericException {

	private static final long serialVersionUID = 201608020138L;

	public ServerException(String error) {
		super(ExceptionConstants.SERVER_EXCEPTION);
		getExceptionBean().setMessage(String.format(ExceptionConstants.SERVER_EXCEPTION.getMessage(), error));
	}

}
