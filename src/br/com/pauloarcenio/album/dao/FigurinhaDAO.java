package br.com.pauloarcenio.album.dao;

import br.com.pauloarcenio.album.base.Base;
import java.util.List;
import br.com.pauloarcenio.album.modelo.entidade.Figurinha;
import br.com.pauloarcenio.album.bd.AlbumBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FigurinhaDAO {

    private static String INSERIR_SQL = "insert into figurinha(numero,pagina,descricao,quantidade)"
            + " values (%d,%d,'%s',%d)";
    private static String ALTERAR_SQL = "update figurinha set descricao= '%s'"
            + ",quantidade= %d where numero= %d";
    private static String REMOVER_SQL = "delete from figurinha where numero= %d";
    private static String SELECIONAR_SQL = "select * from figurinha";
    private static String SELECIONAR_PAGINA_SQL = "select * from figurinha where pagina= %d";
    private static String SELECIONAR_POR_ID = "select * from figurinha where numero= %d";

    public static void inserir(Figurinha figurinha) {
        String sql = String.format(INSERIR_SQL, figurinha.getNumero(),
                figurinha.getPagina(),
                figurinha.getDescricao(),
                figurinha.getQtde());
        AlbumBD.execute(sql, true);
    }

    public static void inserir(int numero) {
        String sql = String.format(INSERIR_SQL,
                numero, (int) Math.ceil(numero / 10) + 1,
                "Indefinida", 0);
        AlbumBD.execute(sql, true);
    }

    public static void alterar(Figurinha figurinha) {
        String sql = String.format(ALTERAR_SQL,
                figurinha.getDescricao(),
                figurinha.getQtde(),
                figurinha.getNumero());
        AlbumBD.execute(sql, true);
    }

    public static void remover(Figurinha figurinha) {
        String sql = String.format(REMOVER_SQL,
                figurinha.getNumero());
        AlbumBD.execute(sql, true);
    }

    private static List<Figurinha> selecionar(String sql) {
        List<Figurinha> lista = new ArrayList<>();
        Connection con = AlbumBD.conectar();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                int numero = rs.getInt("numero");
                byte pagina = rs.getByte("pagina");
                String descricao = rs.getString("descricao");
                byte quantidade = rs.getByte("quantidade");
                lista.add(new Figurinha(numero, pagina, descricao, quantidade));
            }
            AlbumBD.desconectar(con);
        } catch (SQLException e) {
//            System.out.println(e.getLocalizedMessage());
            Base.mensagemDeErro(sql);
            System.exit(1);
        }
        return lista;
    }

    public static List<Figurinha> selecionarTodos() {
        return selecionar(SELECIONAR_SQL);
    }

    public static List<Figurinha> selecionarPorPagina(int pagina) {
        return selecionar(String.format(SELECIONAR_PAGINA_SQL, pagina));
    }

    public static Figurinha selecionaPorID(int numeroFigurinha) {
        Figurinha figurinha = null;
        Connection con = AlbumBD.conectar();
        String sql = String.format(SELECIONAR_POR_ID, numeroFigurinha);
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                int numero = rs.getInt("numero");
                byte pagina = rs.getByte("pagina");
                String descricao = rs.getString("descricao");
                byte quantidade = rs.getByte("quantidade");
                figurinha = new Figurinha(numero, pagina, descricao, quantidade);
            }
            AlbumBD.desconectar(con);
        } catch (SQLException e) {
//            System.out.println(e.getLocalizedMessage());
            Base.mensagemDeErro("Não foi possível executar " + e);
            System.exit(1);
        }
        return figurinha;
    }
}
