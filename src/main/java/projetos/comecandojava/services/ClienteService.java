package projetos.comecandojava.services;

import projetos.comecandojava.entity.Clientes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteService {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DB");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static Map<String, String> response = new HashMap<String, String>();

    /* dados coletados do banco de dados */
    public static List<Clientes> getAll(){
        return entityManager.createNativeQuery("SELECT * FROM clientes").getResultList();
    }

    /* dados são inseridos nas respectivas colunas do bd */
    public static Map<String, String> insertClientes(Map args){
        Clientes clientes = new Clientes();
        clientes.setCpf((String) args.get("cpf"));
        clientes.setNome((String) args.get("nome"));
        clientes.setEmail((String) args.get("email"));
        clientes.setNascimento((String) args.get("nascimento"));
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(clientes);
            entityManager.getTransaction().commit();

            response.put("statusCode", "201");
            response.put("description", "Usuário cadastrado com sucesso!");
            return response;
        } catch(Exception e) {
            response.put("statusCode", "400");
            response.put("description", "Erro ao executar o cadastro...");
            return response;
        }
    }

    public static Map<String, String> alterCliente(Map args) {
        Clientes clientes = new Clientes();
        clientes.setCpf((String) args.get("cpf"));
        clientes.setNome((String) args.get("nome"));
        clientes.setEmail((String) args.get("email"));
        clientes.setNascimento((String) args.get("nascimento"));

        if(entityManager.find(Clientes.class, clientes.getCpf()) != null){
            try {
                entityManager.getTransaction().begin();
                entityManager.merge(clientes);
                entityManager.getTransaction().commit();

                response.put("statusCode", "200");
                response.put("description", "Usuário alterado com sucesso!");

                return response;
            } catch(Exception e) {
                response.put("statusCode", "400");
                response.put("description", "Erro ao executar a alteração...");

                return response;
            }
        }
        return null;
    }

    public static Map deleteCliente(Map args) {
        Clientes clientes = entityManager.find(Clientes.class, args.get("cpf"));

        if(clientes != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(clientes);
            entityManager.getTransaction().commit();

            response.put("statusCode", "200");
            response.put("description", "Deletado com sucesso");
            return response;
        } else {
            response.put("statusCode", "400");
            response.put("description", "Erro ao deletar");
            return response;
        }
    }
}


