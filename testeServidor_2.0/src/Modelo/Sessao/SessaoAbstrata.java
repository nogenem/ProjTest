package Modelo.Sessao;

import java.util.HashMap;
import java.util.Set;

import org.json.JSONObject;

import Modelo.Equipe;
import Modelo.Usuario;
import Modelo.Infos.InfoArquivo;
import Modelo.Infos.InfoPostIt;
import Modelo.Infos.InfoTarefa;

public abstract class SessaoAbstrata {
	
	private Usuario user; //Ao meu ver assim fica melhor mas...
	
	public SessaoAbstrata(Usuario user){
		this.user = user;
	}
	
	public Usuario getUser() {
		return user;
	}
	
	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public Set<String> listarTarefas(Equipe equipe, String projName) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public Set<String> listarProjetos(Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public Set<String> listarArquivos(Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public Set<String> listarMembros(Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public Set<String> listarEquipes(HashMap<String, Equipe> equipes) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public Set<String> listarPostIts(Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void adicionarArquivo(String titulo, String conteudo, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void removerArquivo(String titulo, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public InfoArquivo visualizarArquivo(String titulo, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void modificarArquivo(InfoArquivo info, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void adicionarTarefa(InfoTarefa info, Equipe equipe, String projName) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void removerTarefa(String titulo, Equipe equipe, String projName) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public InfoTarefa visualizarTarefa(String titulo, Equipe equipe, String projName) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void modificarTarefa(InfoTarefa info, Equipe equipe, String projName) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void adicionarMembro(Usuario user, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void removerMembro(Usuario user, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void adicionarProjeto(String projName, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void removerProjeto(String projName, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void adicionarEquipe(String equipeName) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void removerEquipe(String equipeName) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public HashMap<String, Integer> getLoginsAndNiveis(HashMap<String, Usuario> usuarios) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void modificarNivel(String login, int nivel) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void adicionarPostIt(InfoPostIt info, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void removerPostIt(String titulo, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public InfoPostIt visualizarPostIt(String titulo, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
	public void modificarPostIt(InfoPostIt info, Equipe equipe) throws Exception{
		throw new Exception("Voce nao tem permissao para executar esta a�ao.");
	}
	
}
