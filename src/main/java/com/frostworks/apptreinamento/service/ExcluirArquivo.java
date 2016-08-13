package com.frostworks.apptreinamento.service;

import java.io.File;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class ExcluirArquivo implements Serializable {

	private static final long serialVersionUID = 1L;

	public boolean excluirArquivo(String arquivo) {
		if (!StringUtils.isNotBlank(arquivo)) {
			throw new NegocioException("Nome de arquivo vazio.");
		}

		try {
			File arq = new File(arquivo);
			if (!arq.exists()) {
				throw new NegocioException("Arquivo não existe.");
			}
			return arq.delete();
		} catch (Exception e) {
			throw new NegocioException("Erro ao excluir o arquivo.");
		}
	}

	public boolean excluirPasta(File dir) {
		try {
			if (!dir.isDirectory()) {
				throw new NegocioException("Pasta não existe.");
			}
			String[] arqs = dir.list();
			for (int i = 0; i < arqs.length; i++) {
				boolean sucesso = excluirPasta(new File(dir, arqs[i]));
				if (!sucesso) {
					return false;
				}
			}
			return dir.delete();
		} catch (Exception e) {
			throw new NegocioException("Erro ao excluir a pasta.");
		}
	}

	// Deleta todos os arquivos e subdiretorios
	// Retorna verdadeiro se todas as remoções aconteceram com sucesso.
	// Se houve falha, o método será interrompido, e retornará falso.
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		// Agora o diretório está vazio, restando apenas deletá-lo.
		return dir.delete();
	}

}
