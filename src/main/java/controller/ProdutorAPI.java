package controller;

import java.sql.SQLException;
import model.Produtor;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Propriedade;
import persistence.ProdutorDAO;
import persistence.PropriedadeDAO;

@Path("/api")
public class ProdutorAPI {

    ProdutorDAO produtorDAO;
    PropriedadeDAO propriedadeDAO;

    public ProdutorAPI() throws SQLException {
        produtorDAO = new ProdutorDAO();
        propriedadeDAO = new PropriedadeDAO();
    
    }

    @GET
    @Path("/produtor")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produtor> listarProdutores() {
        List<Produtor> produtores = produtorDAO.listar();
        return produtores;
    }

    @GET
    @Path("/produtor/{idprodutor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produtor buscarProdutor(@DefaultValue("0") @PathParam("idprodutor") int idprodutor) {
        Produtor produtor = produtorDAO.buscarPorChavePrimaria(idprodutor);
        return produtor;
    }

    @GET
    @Path("/produtor/{idprodutor}/propriedade")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Propriedade> buscarProdutorPropriedades(@DefaultValue("0") @PathParam("idprodutor") int idprodutor) {
        return produtorDAO.buscarPorChavePrimaria(idprodutor).getPropriedades();
    }
    
    @GET
    @Path("/produtor/{idprodutor}/propriedade/{idpropriedade}")
    @Produces(MediaType.APPLICATION_JSON)
    public Propriedade buscarPropriedade(@DefaultValue("0") @PathParam("idprodutor") int idprodutor,
            @DefaultValue("0") @PathParam("idpropriedade") int idpropriedade) {
        return propriedadeDAO.buscarPorChavePrimaria(idpropriedade);
    }

    @POST
    @Path("/produtor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produtor inserirProdutor(Produtor produtor) {
        produtor = produtorDAO.incluir(produtor);
        return produtor;
    }

    @POST
    @Path("/produtor/{idprodutor}/propriedade")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Propriedade inserirProdutorPropriedade(@DefaultValue("0") @PathParam("idprodutor") int idprodutor, Propriedade propriedade) {
        Produtor produtor = produtorDAO.buscarPorChavePrimaria(idprodutor);
        propriedade.setProdutor(produtor);
        return propriedadeDAO.incluir(propriedade);
    }

    @PUT
    @Path("/produtor/{idprodutor}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produtor alterarProdutor(Produtor produtor) {
        produtor = produtorDAO.alterar(produtor);
        return produtor;
    }

    @PUT
    @Path("/produtor/{idprodutor}/propriedade/{idpropriedade}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Propriedade alterarPropriedade(@DefaultValue("0") @PathParam("idprodutor") int idprodutor,
            @DefaultValue("0") @PathParam("idpropriedade") int idpropriedade,
            Propriedade propriedade
    ) {
        Produtor produtor = produtorDAO.buscarPorChavePrimaria(idprodutor);
        propriedade.setId(idpropriedade);
        propriedade.setProdutor(produtor);
        return propriedadeDAO.alterar(propriedade);
    }

    @DELETE
    @Path("/produtor/{idprodutor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean excluirProdutor(@DefaultValue("0") @PathParam("idprodutor") int idprodutor) {
        return produtorDAO.excluir(idprodutor);
    }

    @DELETE
    @Path("/produtor/{idprodutor}/propriedade/{idpropriedade}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean excluirProdutorPropriedade(@DefaultValue("0") @PathParam("idprodutor") int idprodutor,
            @DefaultValue("0") @PathParam("idpropriedade") int idpropriedade) {
        return propriedadeDAO.excluir(idpropriedade);
    }

    
    
    
}

