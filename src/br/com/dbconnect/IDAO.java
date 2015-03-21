package br.com.dbconnect;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * <I><B>Nota:</B> Interface para m�todos utilizados no Banco de Dados.
 */
public interface IDAO {

	/**
	 * Fun��o para salvar no MongoDB que recebe um objeto como par�metro.
	 * @param classe Objeto de alguma classe.
	 * @return boolean, true para sucesso ao salvar e false para alguma falha.
	 */
	boolean save(Object classe);
	
	/**
	 * Fun��o para salvar no MongoDB que recebe um json como par�metro.
	 * @param json String com padr�o json.
	 * @return boolean, true para sucesso ao salvar e false para alguma falha.
	 */
	boolean save(String json);
	
	/**
	 * Fun��o para atualizar um registro no MongoDB que recebe um objeto do tipo BasicDBObject.
	 * @param query json para busca de um registro no banco.
	 * @param update json que substituir� o registro encontrado.
	 * @return boolean, true para sucesso ao atualizar e false para alguma falha.
	 */
	boolean update(BasicDBObject query, BasicDBObject update);
	
	/**
	 * Fun��o para atualizar um registro no MongoDB que recebe um json.
	 * @param query json para busca de um registro no banco.
	 * @param update json que substituir� o registro encontrado.
	 * @return boolean, true para sucesso ao atualizar e false para alguma falha.
	 */
	boolean update(String query, String update);

	/**
	 * Fun��o para excluir um registro no MongoDB que recebe um objeto como par�metro. 
	 * @param classe Objeto de alguma classe.
	 * @return boolean, true para sucesso ao excluir e false para alguma falha.
	 */
	boolean delete(Object classe);

	/**
	 * Fun��o para excluir um registro no MongoDB que recebe um json como par�metro.  
	 * @param json String com padr�o json.
	 * @return boolean, true para sucesso ao excluir e false para alguma falha.
	 */
	boolean delete(String json);

	/**
	 * Fun��o para localizar apenas um registro que recebe uma classe como par�metro.
	 * @param classe Objeto de alguma classe.
	 * @return Objeto do tipo DBObject.
	 */
	DBObject findOne(Object classe);

	/**
	 * Fun��o para localizar apenas um registro que recebe um json como par�metro.
	 * @param json String com padr�o json.
	 * @return Objeto do tipo DBObject.
	 */
	DBObject findOne(String json);

	/**
	 * Fun��o para localizar todos os registros de uma tabela.
	 * @return Lista de DBObject.
	 */
	List<DBObject> findAll();

	List<DBObject> findKeyValue(Object keyValue);

	List<DBObject> findKeyValue(String keyValue);

	/**
	 * Fun��o para localizar registro(s).
	 * @param limit Quantidade de registro para o retorno.
	 * @param order true para pegar os registros na tabela do MongoDB de forma Ascendente e false para Descendente.
	 * @return Lista que cont�m dados do tipo json.
	 */
	List<Object> getRegister(int limit, boolean order);

	/**
	 * Fun��o para localizar registro com o mesmo padr�o json com par�metro BasicDBObject.
	 * @param register Objeto do tipo BasicDBObject para busca no banco.
	 * @return boolean, true para sucesso ao encontrar o registro e false para alguma falha.
	 */
	boolean getEspecificRegister(BasicDBObject register);

	/**
	 * Fun��o para localizar registro com o mesmo padr�o json com par�metro String.
	 * @param register String com padr�o json para busca no banco.
	 * @return boolean, true para sucesso ao encontrar o registro e false para alguma falha.
	 */
	boolean getEspecificRegister(String register);
}
