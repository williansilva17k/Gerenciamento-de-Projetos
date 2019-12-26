/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cargo;
import model.bean.Funcionario;
import model.bean.Login;
import model.bean.Projeto;
import model.bean.Status;
import model.bean.Tarefa;

/**
 *
 * @author WILLIAN
 */
public class ProjetoDAO {

    public void cadastroFuncionario(Funcionario f) {

        String sql = "INSERT INTO funcionario(id_login,nome_func,cpf,id_cargo) VALUES ((SELECT id_login FROM login WHERE nome_user=?),?,?,(SELECT id_cargo FROM cargo WHERE nome_cargo =?))";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getNome_login());
            stmt.setString(2, f.getNome_func());
            stmt.setString(3, f.getCpf());
            stmt.setString(4, f.getCargo().getNome_cargo());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionario\n" + "Errro: " + e);
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }

    }

    public List<Cargo> readCargo() {
        String sql = "SELECT nome_cargo FROM cargo;";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cargo> cargos = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cargo c = new Cargo();
                c.setNome_cargo(rs.getString("nome_cargo"));
                cargos.add(c);
            }
        } catch (SQLException ex) {
            System.err.println("Erro= " + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return cargos;
    }

    public List<Login> readLogin() {
        String sql = "SELECT nome_user FROM login;";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Login> logins = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Login l = new Login();
                l.setNome_user(rs.getString("nome_user"));
                logins.add(l);
            }
        } catch (SQLException ex) {
            System.err.println("Erro= " + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return logins;
    }

    public List<Status> readStatus() {
        String sql = "SELECT nome_status FROM status";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Status> status = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Status s = new Status();
                s.setNome_status(rs.getString("nome_status"));
                status.add(s);
            }
        } catch (SQLException ex) {
            System.err.println("Erro = " + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return status;
    }

    public List<Projeto> readProjeto() {
        String sql = "SELECT nome_projeto FROM projeto";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Projeto> projetos = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Projeto p = new Projeto();
                p.setNome_projeto(rs.getString("nome_projeto"));
                projetos.add(p);
            }
        } catch (SQLException ex) {
            System.err.println("Erro= " + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return projetos;
    }

    public List<Projeto> readProjetoT() {
        String sql = "SELECT p.id_projeto as id,p.nome_projeto as nome,s.nome_status as status,p.descricao as descricao FROM projeto p,status s where p.id_status=s.id_status";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Projeto> projetos = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Projeto p = new Projeto();
                p.setId_projeto(rs.getInt("id"));
                p.setNome_projeto(rs.getString("nome"));
                p.setStatus(rs.getString("status"));
                p.setDescricao(rs.getString("descricao"));
                projetos.add(p);
            }
        } catch (SQLException ex) {
            System.err.println("Erro = " + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return projetos;
    }

    public List<Funcionario> readFuncionario() {
        String sql = "select f.id_func as ID,f.nome_func as Nome,f.cpf as CPF,c.nome_cargo as Cargo,l.nome_user as Login from funcionario f,cargo c,login l where f.id_cargo=c.id_cargo and f.id_login =l.id_login order by f.id_func";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                Cargo c = new Cargo();
                f.setId_func(rs.getInt("ID"));
                f.setNome_func(rs.getString("Nome"));
                f.setCpf(rs.getString("CPF"));
                c.setNome_cargo(rs.getString("Cargo"));
                f.setNome_login(rs.getString("Login"));
                f.setCargo(c);
                funcionarios.add(f);
            }
        } catch (SQLException ex) {
            System.err.println("Erro = " + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return funcionarios;
    }

    public List<Tarefa> readTarefa() {
        String sql = "SELECT t.id_tarefa as Id,t.nome_tarefa as Nome,p.nome_projeto as Projeto,t.data_ini as Data_Inicio,t.data_fin as Data_Final,f.nome_func as Funcionario,t.descricao as Descricao,s.nome_status as Status from tarefa t,projeto p,funcionario f,status s where t.id_projeto=p.id_projeto and t.id_func=f.id_func and t.id_status=s.id_status order by p.id_projeto";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Tarefa> tarefas = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Tarefa t = new Tarefa();
                t.setId_tarefa(rs.getInt("Id"));
                t.setNome_tarefa(rs.getString("Nome"));
                t.setNome_projeto(rs.getString("Projeto"));
                t.setData_ini(rs.getString("Data_Inicio"));
                t.setData_fin(rs.getString("Data_Final"));
                t.setNome_func(rs.getString("Funcionario"));
                t.setDescricao(rs.getString("Descricao"));
                t.setNome_status(rs.getString("Status"));
                tarefas.add(t);
            }
        } catch (SQLException ex) {
            System.err.println("Erro = " + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return tarefas;
    }
    
    public List<Tarefa> readTarefa(String nome) {
        String sql = "SELECT t.id_tarefa as Id,t.nome_tarefa as Nome,p.nome_projeto as Projeto,t.data_ini as Data_Inicio,t.data_fin as Data_Final,f.nome_func as Funcionario,t.descricao as Descricao,s.nome_status as Status from tarefa t,projeto p,funcionario f,status s where t.id_projeto=p.id_projeto and t.id_func=f.id_func and t.id_status=s.id_status and f.nome_func = ? order by p.id_projeto";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Tarefa> tarefas = new ArrayList<>();
        try {
            Tarefa t = new Tarefa();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();

            while (rs.next()) {               
                t.setId_tarefa(rs.getInt("Id"));
                t.setNome_tarefa(rs.getString("Nome"));
                t.setNome_projeto(rs.getString("Projeto"));
                t.setData_ini(rs.getString("Data_Inicio"));
                t.setData_fin(rs.getString("Data_Final"));
                t.setNome_func(rs.getString("Funcionario"));
                t.setDescricao(rs.getString("Descricao"));
                t.setNome_status(rs.getString("Status"));
                tarefas.add(t);
            }
        } catch (SQLException ex) {
            System.err.println("Erro = " + ex);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return tarefas;
    }

    public void deleteFuncionario(Funcionario f) {
        String sql = "delete from funcionario where nome_func=?";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getNome_func());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Deletado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar funcionario verifique se o mesmo possui alguma tarefa pendente!\n");
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

    public void deleteLogin(Funcionario f) {
        String sql = "delete from login where nome_user=(SELECT l.nome_user FROM funcionario f,login l where f.id_login=l.id_login and f.nome_func=?)";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getNome_func());

            stmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

    public Integer validateFuncionarioTarefa(Funcionario f) {
        String sql = "SELECT COUNT(id_tarefa) as qtd FROM tarefa WHERE id_func=(SELECT id_func FROM funcionario where nome_func=?)";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer qtd = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, f.getNome_func());
            rs = stmt.executeQuery();
            while (rs.next()) {
                qtd = rs.getInt("qtd");
            }
        } catch (SQLException e) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return qtd;
    }

    public Integer validateProjetoTarefa(Projeto p) {
        String sql = "SELECT COUNT(id_tarefa) as qtd from tarefa where id_projeto = (select id_projeto from projeto where nome_projeto = ?)";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer qtd = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome_projeto());
            rs = stmt.executeQuery();
            while (rs.next()) {
                qtd = rs.getInt("qtd");
            }
        } catch (SQLException e) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return qtd;
    }

    public boolean validateLogin(String login,String senha) {
        String sql = "select * from login where nome_user = ? and senha_user = ?";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
               check=true;
            }
            
        } catch (SQLException e) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);}
        finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return check;
    }
    
    public boolean validatePermission(String login) {
        String sql = "select * from login where nome_user = ? ";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int p = 0;

        boolean check = false;

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, login);

            rs = stmt.executeQuery();

            while (rs.next()) {
                p = rs.getInt("permission");
            }
            if (p == 1) {
                check = true;
            }

        } catch (SQLException e) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return check;
    }
    

    public void deleteProjeto(Projeto p) {
        String sql = "delete from projeto where nome_projeto=?";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome_projeto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Deletado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar projeto\n" + "Errro: " + e);
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

    public void deleteTarefa(Tarefa t) {
        String sql = "delete from tarefa where nome_tarefa=?";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, t.getNome_tarefa());

            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar Tarefa\n" + "Errro: " + e);
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }
    
    public String pegaNomeUser(String user) {
        String sql = "SELECT f.nome_func as nome FROM funcionario f,login l where f.id_login=l.id_login and l.nome_user=?";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nome = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, user);

            rs = stmt.executeQuery();

            while (rs.next()) {
                nome = rs.getString("nome");
            }

        } catch (SQLException e) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return nome;
    }

    public void deleteProjetoTarefa(Projeto p) {
        String sql = "delete from tarefa where id_projeto=(select id_projeto from projeto where nome_projeto=?)";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome_projeto());

            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar projetoTareafas\n" + "Errro: " + e);
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

    public void registerLogin(Login l) {
        String sql = "INSERT INTO login(nome_user,senha_user,permission) VALUES (?,?,?)";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, l.getNome_user());
            stmt.setString(2, l.getSenha_user());
            stmt.setInt(3, l.getPermission());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar login\n" + "Errro: " + e);
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

    public void updateProjeto(Projeto p) {
        String sql = "update projeto set id_status=(select id_status from status where nome_status=?"
                + "),descricao=? where nome_projeto=?";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getStatus());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getNome_projeto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar projeto\n" + "Errro: " + e);
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

    public void updateTarefa(Tarefa t) {
        String sql = "update tarefa set id_func=(SELECT id_func from funcionario where nome_func=?),id_status=(SELECT id_status from status where nome_status=?),descricao=? where nome_tarefa=?";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, t.getNome_func());
            stmt.setString(2, t.getNome_status());
            stmt.setString(3, t.getDescricao());
            stmt.setString(4, t.getNome_tarefa());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar projeto\n" + "Errro: " + e);
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }
    
    public void updateTarefaPessoa(Tarefa t, String nome) {
        String sql = "update tarefa set id_status=(SELECT id_status FROM status where nome_status=?) where id_func=(select id_func from funcionario where nome_func=?) and nome_tarefa=?";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, t.getNome_status());
            stmt.setString(2, nome);
            stmt.setString(3, t.getNome_tarefa());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar projeto\n" + "Errro: " + e);
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

    public void registerTarefa(Tarefa t) {
        String sql = "insert into tarefa(nome_tarefa,id_projeto,data_ini,data_fin,id_func,id_status,descricao) values (?,(select id_projeto from projeto where nome_projeto = ?),?,?,(SELECT id_func from funcionario where nome_func=?),2,?)";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, t.getNome_tarefa());
            stmt.setString(2, t.getNome_projeto());
            stmt.setString(3, t.getData_ini());
            stmt.setString(4, t.getData_fin());
            stmt.setString(5, t.getNome_func());
            stmt.setString(6, t.getDescricao());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Tarefa Registrada");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar tarefa\n" + "Errro: " + e);
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

    public void registerProjeto(Projeto p) {
        String sql = "insert into projeto(nome_projeto,id_status,descricao) values(?,2,?)";
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome_projeto());
            stmt.setString(2, p.getDescricao());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar projeto\n" + "Errro: " + e);
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }
}
