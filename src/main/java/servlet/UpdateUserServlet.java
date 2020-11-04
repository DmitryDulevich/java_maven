package servlet;

import dao.UserDao;
import dao.UserDaoFromDBImpl;
import entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateuser")
public class UpdateUserServlet extends HttpServlet {

    private UserDao userDao = new UserDaoFromDBImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = userDao.findById(Long.valueOf(req.getParameter("id")));

        req.setAttribute("user", user);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateuser.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userDao.findById(Long.valueOf(req.getParameter("id")));

        user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));

        userDao.update(user);

        resp.sendRedirect("/alluser");
    }
}
