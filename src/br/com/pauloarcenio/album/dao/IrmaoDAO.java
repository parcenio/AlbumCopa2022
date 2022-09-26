package br.com.pauloarcenio.album.dao;

import br.com.pauloarcenio.album.base.Base;
import br.com.pauloarcenio.album.bd.AlbumBD;
import br.com.pauloarcenio.album.modelo.entidade.Irmao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IrmaoDAO {

    private static String INSERIR_SQL = "insert into irmao(nome,contato)"
            + " values ('%s','%s')";
    private static String ALTERAR_SQL = "update irmao set nome= '%s'"
            + ",contato= '%s' where id= %d";
    private static String REMOVER_SQL = "delete from irmao where id= %d";
    private static String SELECIONAR_SQL = "select * from irmao";
    private static String SELECIONAR_POR_ID = "select * from irmao where id= %d";

    public static void inserir(Irmao irmao) {
        String sql = String.format(INSERIR_SQL,
                irmao.getNome(),
                irmao.getContato());
        AlbumBD.execute(sql, true);
    }

    public static void alterar(Irmao irmao) {
        String sql = String.format(ALTERAR_SQL,
                irmao.getNome(),
                irmao.getContato(),
                irmao.getId());
        AlbumBD.execute(sql, true);
    }

    public static void remover(Irmao irmao) {
        String sql = String.format(REMOVER_SQL,
                irmao.getId());
        AlbumBD.execute(sql, true);
    }

    public static List<Irmao> selecionarTodos() {
        List<Irmao> lista = new ArrayList<>();
        Connection con = AlbumBD.conectar();
        try {
            ResultSet rs = con.createStatement().executeQuery(SELECIONAR_SQL);
            while (rs.next()) {
                byte id = rs.getByte("id");
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");
                lista.add(new Irmao(id, nome, contato));
            }
            AlbumBD.desconectar(con);
        } catch (SQLException e) {
//            System.out.println(e.getLocalizedMessage());
            Base.mensagemDeErro("Não foi possível executar "+ e);
            System.exit(1);
        }
        return lista;
    }
    public static Irmao selecionaPorID(byte idIrmao) {
        Irmao irmao = null;
        Connection con = AlbumBD.conectar();
        String sql = String.format(SELECIONAR_POR_ID, idIrmao);
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                byte id = rs.getByte("id");
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");
                irmao = new Irmao(id, nome, contato);
            }
            AlbumBD.desconectar(con);
        } catch (SQLException e) {
//            System.out.println(e.getLocalizedMessage());
            Base.mensagemDeErro("Não foi possível executar "+ e);
            System.exit(1);
        }
        return irmao;
    }

}
