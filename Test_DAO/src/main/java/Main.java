import com.Dao.FrutasDAOImpl;
import com.Utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection con = ConnectionManager.getInstance().getConnection();
        FrutasDAOImpl frutasDAO = new FrutasDAOImpl(con);
        frutasDAO.MostrartarFrutas();
    }
}
