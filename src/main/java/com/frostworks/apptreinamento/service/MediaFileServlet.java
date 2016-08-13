package com.frostworks.apptreinamento.service;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.omnifaces.servlet.FileServlet;

@WebServlet("/media/*")
public class MediaFileServlet extends FileServlet {

	private File pasta;
	
	@Override
	public void init() throws ServletException{
		pasta = new File("C:\\Plataforma\\Gestao\\Treinamento\\Arquivos\\imagem\\avaliacao\\");
	}

	@Override
	protected File getFile(HttpServletRequest request) throws IllegalArgumentException {
		String pathInfo = request.getPathInfo();
		
		if (pathInfo == null || pathInfo.isEmpty() || "/".equals(pathInfo)) {
			throw new IllegalArgumentException();
		}
		
		return new File(pasta, pathInfo);
	}
	
	
	
}
