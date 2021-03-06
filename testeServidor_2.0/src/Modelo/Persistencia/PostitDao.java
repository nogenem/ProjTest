package Modelo.Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Modelo.Infos.InfoPostIt;

public class PostitDao extends Dao {

	public PostitDao(ConexaoBanco c, String tabela) {
		super(c, tabela);
	}

	public void add(InfoPostIt info, int id_equipe) throws Exception
	{
		HashMap<String,String> dados = new HashMap<>();
		dados.put("TITULO", info.getTitulo());
		dados.put("CONTEUDO", info.getConteudo());
		dados.put("ID_EQUIPE", ""+id_equipe);
		dados.put("ID_USUARIO", ""+info.getIdEmissor());
		
		System.err.println(info.getIdEmissor());
		System.err.println(id_equipe);
		this.insert(dados);
	}
	
	public void remove(String titulo, int id_equipe) throws Exception
	{
		HashMap<String, String> cond = new HashMap<>();
		cond.put("TITULO", titulo);
		cond.put("ID_EQUIPE", ""+id_equipe);
		
		this.delete(cond);
	}
	
	public void removeAll(int id_equipe) throws Exception //Vai remover todos os post-its da equipe q vem como parametro
	{
		HashMap<String, String> cond = new HashMap<>();
		cond.put("ID_EQUIPE", ""+id_equipe);
		
		this.delete(cond);
	}
	
	public InfoPostIt view(String titulo, int id_equipe) throws Exception
	{
		
		HashMap<String, String> cond = new HashMap<>();
		cond.put("TITULO", titulo);
		cond.put("ID_EQUIPE", ""+ id_equipe);
		
		this.select(cond);
		ResultSet result = this.getResultSet();
		
		if(result != null && result.next())
		{
			InfoPostIt info = new InfoPostIt( result.getString("TITULO"), result.getString("CONTEUDO"), result.getInt("ID_USUARIO") );
			
			UsuarioDao usuarioDao = new UsuarioDao(new ConexaoBanco(), "USUARIO");
			
			cond = new HashMap<>();
			cond.put("ID_USUARIO", ""+info.getIdEmissor());
			
			usuarioDao.select(cond);   
			
			result = usuarioDao.getResultSet();
			if(result != null && result.next())
			{
				info.setLoginEmissor( result.getString("LOGIN") );
			}
			return info;
		}
		return null;
	}
	
	public void edit(InfoPostIt info, int id_equipe) throws Exception
	{
		HashMap<String, String> dados = new HashMap<>();
		dados.put("TITULO", info.getTitulo());
		dados.put("CONTEUDO", info.getConteudo());
		
		HashMap<String, String> cond = new HashMap<>();
		cond.put("TITULO", info.getTitulo());
		cond.put("ID_EQUIPE", ""+ id_equipe);
		
		this.update(dados, cond);
	}
	
	public Set<String> list(int id_equipe) throws Exception
	{
		HashMap<String, String> cond = new HashMap<>();
		cond.put("ID_EQUIPE", ""+id_equipe);
		
		this.select(cond);
		ResultSet result = this.getResultSet();
		Set<String> setReturn = new HashSet<>();
		
		while( result != null && result.next() )
		{
			setReturn.add( result.getString( "TITULO" ) );
		}
		return setReturn;
	}
	
	public boolean exist(String titulo, int id_equipe) throws Exception
	{
		HashMap<String, String> cond = new HashMap<>();
		cond.put("TITULO", titulo);
		cond.put("ID_EQUIPE", ""+ id_equipe);
		
		this.select(cond);
		
		ResultSet result = this.getResultSet();
		return result != null && result.next();
	}
}
