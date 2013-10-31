package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Modelo.Infos.InfoArquivo;
import Modelo.Infos.InfoTarefa;

public class Equipe {

	private String nome;
	private HashMap<String, Arquivo> arquivos;
	private HashMap<String, Projeto> projetos;
	private HashMap<String, Usuario> membros;

	public Equipe(String nome){
		this.nome = nome;
		this.arquivos = new HashMap<>();
		this.projetos = new HashMap<>();
		this.membros = new HashMap<>();
	}
	
	public String getNome(){
		return nome;
	}
	
	public Set<String> listarMembros(){
		return membros.keySet();
	}
	
	public Set<String> listarArquivos(){
		return arquivos.keySet();
	}
	
	public Set<String> listarProjetos(){
		return projetos.keySet();
	}
	
	public Set<String> listarTarefas(String projName) throws Exception{
		if(!projetos.containsKey(projName))
			throw new Exception("Projeto nao encontrado.");
		Projeto proj = projetos.get(projName);
		return proj.listarTarefas();
	}
	
	public void adicionarArquivo(String titulo, String conteudo) throws Exception{
		if(arquivos.containsKey(titulo))
			throw new Exception("Arquivo ja adicionado.");
		InfoArquivo info = new InfoArquivo(titulo, conteudo);
		arquivos.put(titulo, new Arquivo(info));
	}
	
	public void removerArquivo(String titulo) throws Exception{
		if(!arquivos.containsKey(titulo))
			throw new Exception("Arquivo nao encontrado.");
		arquivos.remove(titulo);
	}
	
	public InfoArquivo visualizarArquivo(String titulo) throws Exception{
		if(!arquivos.containsKey(titulo))
			throw new Exception("Arquivo nao encontrado.");
		Arquivo tmp = arquivos.get(titulo);
		return tmp.visualizarArquivo();
	}

	public void modificarArquivo(InfoArquivo info){
		Arquivo tmp = arquivos.get(info.getTitulo());
		tmp.modificarArquivo(info);
	}

	public void adicionarTarefa(InfoTarefa info, String projName) throws Exception{
		if(!projetos.containsKey(projName))
			throw new Exception("Projeto nao encontrado.");
		Projeto tmp = projetos.get(projName);
		tmp.adicionarTarefa(info);
	}
	
	public void removerTarefa(String titulo, String projName) throws Exception{
		if(!projetos.containsKey(projName))
			throw new Exception("Projeto nao encontrado.");
		
		Projeto tmp = projetos.get(projName);
		tmp.removerTarefa(titulo);
	}
	
	public InfoTarefa visualizarTarefa(String titulo, String projName) throws Exception{
		if(!projetos.containsKey(projName))
			throw new Exception("Projeto nao encontrado.");
		
		Projeto tmp = projetos.get(projName);
		return tmp.visualizarTarefa(titulo);
	}
	
	public void modificarTarefa(InfoTarefa info, String projName) throws Exception{
		if(!projetos.containsKey(projName))
			throw new Exception("Projeto nao encontrado.");
		Projeto tmp = projetos.get(projName);
		tmp.modificarTarefa(info);
	}	
	
	public void adicionarMembro(Usuario user) throws Exception{
		if(membros.containsKey(user.getLogin()))
			throw new Exception("Usuario ja � membro desta equipe.");
		else if(user.getEquipe() != null)
			throw new Exception("Usuario ja � membro de uma equipe.");
		membros.put(user.getLogin(), user);
		user.setEquipe(this);
	}
	
	public void removerMembro(Usuario user) throws Exception{
		if(!membros.containsKey(user.getLogin()))
			throw new Exception("Usuario nao pertence a esta equipe.");
		membros.remove(user.getLogin());
		user.setEquipe(null);
	}
	
	public void adicionarProjeto(String projName) throws Exception{
		if(projetos.containsKey(projName))
			throw new Exception("Projeto ja existe.");
		projetos.put(projName, new Projeto(projName));
	}
	
	public void removerProjeto(String projName) throws Exception{
		if(!projetos.containsKey(projName))
			throw new Exception("Projeto nao encontrado.");
		projetos.remove(projName);
	}
	
	public boolean isMember(Usuario user){
		return membros.containsKey(user.getLogin());
	}
}