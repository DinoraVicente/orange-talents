package projetos.comecandojava.controller;

import org.springframework.web.bind.annotation.*;
import projetos.comecandojava.entity.Clientes;
import projetos.comecandojava.services.ClienteService;

import java.util.*;

/*
Faz o intermédio do frontEnd com o resto do BackEnd e implementa os padrões Rest.
*/
@RestController
public class ClientController {

    private static Map<String, String> map = new HashMap<String, String>();

    /*
       Onde "pega-se" os dados dentro do BD através do endpoint.
    */
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Clientes> getClientes() {
        return ClienteService.selectClientes();
    }

    /* onde as informações são postadas, uma requisição para o servidor enviando os seus dados do formulário. */
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Map postClientes(@RequestBody Clientes clientes){
        map.put("cpf", clientes.getCpf());
        map.put("nome", clientes.getNome());
        map.put("email", clientes.getEmail());
        map.put("nascimento", clientes.getNascimento());

        return ClienteService.insertClientes(map);
    }

    /* Patch altera apenas algumas informações */
    @RequestMapping(value = "/patch", method = RequestMethod.PATCH)
    public Map patchCliente(@RequestBody Clientes clientes){
        map.put("cpf", clientes.getCpf());
        map.put("nome", clientes.getNome());
        map.put("email", clientes.getEmail());
        map.put("nascimento", clientes.getNascimento());

        return ClienteService.alterCliente(map);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Map deleteCliente(@RequestBody Clientes clientes){
        map.put("cpf", clientes.getCpf());
        return ClienteService.deleteCliente(map);
    }
}
