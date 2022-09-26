package br.com.pauloarcenio.album.bd;

import br.com.pauloarcenio.album.base.Base;
import br.com.pauloarcenio.album.dao.FigurinhaDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AlbumBD {

    public static void inicializarBD() {
        String sql;
        Connection con = conectar();
        sql = "Create table if not exists figurinha "
                + "(numero int not null primary key, "
                + "pagina int not null, "
                + "descricao varchar(30) not null, "
                + "quantidade int not null)";
        execute(con, sql);
        sql = "Create table if not exists irmao "
                + "(id int not null auto_increment primary key, "
                + "nome varchar(20) not null, "
                + "contato varchar(20) not null)";
        execute(con, sql);
        desconectar(con);
    }

    public static void popular() {
        int qtdeFigurinhas = FigurinhaDAO.selecionarTodos().size();
        if (qtdeFigurinhas < 300) {
            for (int i = 1; i <= 300; i++) {
                FigurinhaDAO.inserir(i);
            }
        }
        
    }

    public static Connection conectar() {
        Connection con = null;
        final String USUARIO = "root";
        final String SENHA = "arcenio1213";
        final String URL = "jdbc:mysql://localhost/albumcopa2022";
        try {
            con = DriverManager.getConnection(URL,
                    USUARIO, SENHA);
        } catch (SQLException ex) {
            Base.mensagemDeErro("Não foi possível conectar ao banco de dados. "
                    + "Verifique e tente posteriormente");
            System.exit(1);
        }
        return con;
    }

    public static void desconectar(Connection c) {
        try {
            c.close();
        } catch (SQLException ex) {
        }
    }

    public static void execute(String sql, boolean continuaNoErro) {
        // Fazer versão para conexões seguidas não desconectar
        Connection con = conectar();
        try {
            con.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            if (!continuaNoErro) {
                System.exit(1);
            }
        }
        desconectar(con);
    }

    private static void execute(Connection con, String sql) {
        try {
            con.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
//            System.out.println(ex.getLocalizedMessage());
            Base.mensagemDeErro("Não foi possível executar\n" + sql);
            System.exit(1);
        }
    }
}
