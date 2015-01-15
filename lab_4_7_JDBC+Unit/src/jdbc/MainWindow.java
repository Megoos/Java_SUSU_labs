package jdbc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class MainWindow {

	static JFrame frame1;
	static Container pane;
	static JButton btnShowUsersAndMessages, btnCountUsers,
			btnTopTenRecentlyLoggedIn, btnTopTenDidntLogInForAlongTime,
			btnLogin, btnEnterMessage, btnExit;
	static Insets insets;
	static String logged_in_user = "";

	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}

		final DB db = new DB();
		final BufferedReader bufr = new BufferedReader(new InputStreamReader(
				System.in));

		// Создание окна
		frame1 = new JFrame("JDBC application");
		frame1.setSize(400, 260); // Размеры окна
		frame1.setLocationRelativeTo(null); // Окно по центру
		pane = frame1.getContentPane();
		insets = pane.getInsets();
		pane.setLayout(null);

		// Кнопки
		btnShowUsersAndMessages = new JButton(
				"Вывод имен пользователей и сообщений");
		btnCountUsers = new JButton("Подсчет количества пользователей");
		btnTopTenRecentlyLoggedIn = new JButton(
				"Вывод ТОП-10 недавно входивших в систему пользователей");
		btnTopTenDidntLogInForAlongTime = new JButton(
				"Вывод топ-10 давно не входивших в систему пользователей");
		btnLogin = new JButton("Вход в систему");
		btnEnterMessage = new JButton("Ввод сообщения");
		btnExit = new JButton("Выход из системы");

		pane.add(btnShowUsersAndMessages);
		pane.add(btnCountUsers);
		pane.add(btnTopTenRecentlyLoggedIn);
		pane.add(btnTopTenDidntLogInForAlongTime);
		pane.add(btnLogin);
		pane.add(btnEnterMessage);
		pane.add(btnExit);

		// Расположение кнопок на экране
		btnShowUsersAndMessages.setBounds(5, 5, 370, 30);
		btnCountUsers.setBounds(5, 35, 370, 30);
		btnTopTenRecentlyLoggedIn.setBounds(5, 65, 370, 30);
		btnTopTenDidntLogInForAlongTime.setBounds(5, 95, 370, 30);
		btnLogin.setBounds(5, 125, 370, 30);
		btnEnterMessage.setBounds(5, 155, 370, 30);
		btnExit.setBounds(5, 185, 370, 30);

		frame1.setVisible(true);

		// Экшены для кнопок

		btnShowUsersAndMessages.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					db.printUsersMessages();
				} catch (SQLException exc) {
					exc.printStackTrace();
				}
			}
		});

		btnCountUsers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Количество пользователей: "
							+ db.UsersQuantity());
				} catch (SQLException exc) {
					exc.printStackTrace();
				}
			}
		});

		btnTopTenRecentlyLoggedIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(db.topTen("Desc"));
				} catch (SQLException exc) {
					exc.printStackTrace();
				}
			}
		});

		btnTopTenDidntLogInForAlongTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(db.topTen("Asc"));
				} catch (SQLException exc) {
					exc.printStackTrace();
				}
			}
		});

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Логин:");
					String login = bufr.readLine();
					System.out.println("Пароль:");
					String pass = bufr.readLine();
					if (db.login(login, pass) != "")
						System.out.println(db.login(login, pass));
					else
						System.out.println("Неправильно введены логин или пароль");
				} catch (SQLException exc) {
					exc.printStackTrace();
				} catch (IOException exc) {
					exc.printStackTrace();
				}
			}
		});

		btnEnterMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (logged_in_user != "") {
						System.out.println("Введите сообщение:");

						String message = bufr.readLine();
						System.out.println(logged_in_user + "\t" + message);
						db.newMessage(logged_in_user, message);
					}
				} catch (SQLException exc) {
					exc.printStackTrace();
				} catch (IOException exc) {
					exc.printStackTrace();
				}
			}
		});

		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (logged_in_user != "") {
					System.out.println("Пользователь " + logged_in_user
							+ " вышел из системы");
					logged_in_user = "";
				}
			}
		});
	}

}