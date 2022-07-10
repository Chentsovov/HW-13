import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Ex1Main {
//Напишите программу, которая будет взаимодействовать с API https://jsonplaceholder.typicode.com.
//
//Можно пользоваться стандартными возможностями Java (HttpURLConnection),
// либо познакомиться самостоятельно со сторонними библиотеками
// (Apache Fluent API, Apache HTTPClient, Jsoup).

//Задание 1
//Программа должна содержать методы для реализации следующего функционала:
//создание нового объекта в https://jsonplaceholder.typicode.com/users. Возможно, вы не увидите обновлений на сайте.
// Метод создания работает правильно, если в ответ на JSON с объектом вернулся такой же JSON, но с полем id
// со значением на 1 больше, чем самый большой id на сайте.
//обновление объекта в https://jsonplaceholder.typicode.com/users. Возможно, обновления не будут видны на
// сайте напрямую. Будем считать, что метод работает правильно, если в ответ на запрос придет обновленный JSON
// (он должен быть точно таким же, какой вы отправляли).
//удаление объекта из https://jsonplaceholder.typicode.com/users. Здесь будем считать корректным результат -
// статус из группы 2хх в ответ на запрос.
//получение информации обо всех пользователях https://jsonplaceholder.typicode.com/users
//получение информации о пользователе с определенным id https://jsonplaceholder.typicode.com/users/{id}
//получение информации о пользователе с опредленным username - https://jsonplaceholder.typicode.com/users?username={username}

//Задание 2#
//Дополните программу методом, который будет выводить все комментарии к последнему посту определенного пользователя и записывать их в файл.
//https://jsonplaceholder.typicode.com/users/1/posts Последним будем считать пост с наибольшим id.
//https://jsonplaceholder.typicode.com/posts/10/comments
//Файл должен называться "user-X-post-Y-comments.json", где Х - номер пользователя, Y - номер поста.

//Задание 3#
//Дополните программу методом, который будет выводить все открытые задачи для пользователя Х.
//https://jsonplaceholder.typicode.com/users/1/todos.
//Открытыми считаются все задачи, у которых completed = false.
        private static final String ADRESS =        "https://jsonplaceholder.typicode.com";
        private static final String ADD_USER =      "https://jsonplaceholder.typicode.com/users";
        private static final String EDIT_USER =     "https://jsonplaceholder.typicode.com/users";
        private static final String REMOVE_USER =   "https://jsonplaceholder.typicode.com/users";
        private static final String GET_USER =      "https://jsonplaceholder.typicode.com/users";

        public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
            HttpUtil httpUtil = new HttpUtil(ADRESS);
            User defaultUser = UserService.getDefaultUser();
            System.out.println(" Default user: \n" + defaultUser + "\n");
//создание нового объекта
        //    User addedUser = httpUtil.sendPost(URI.create(ADD_USER), defaultUser);
        //    System.out.println("User added: \n" + addedUser + "\n");
        //    defaultUser.setName("Olch Батькович Имерёк");
//обновление объекта
       //   User editeddUser = httpUtil.sendPut(URI.create(String.format("%s/%d", EDIT_USER, defaultUser.getId())), defaultUser);
       //   System.out.println("User edited: \n" + editeddUser + "\n");
//удаление объекта
        //  System.out.println("User removed: \n" + httpUtil.sendDelete(URI.create(String.format("%s/%d", REMOVE_USER, defaultUser.getId()))) + "\n");
//получение информации обо всех пользователях
        //       System.out.println("Get all users: " + httpUtil.sendGetWithListOfResults(URI.create(GET_USER)));
//получение информации о пользователе с определенным id
        //    System.out.println("Get user id = 1: \n" + httpUtil.sendGet(URI.create(String.format("%s?id=%d", GET_USER, 5))) + "\n");
//получение информации о пользователе с опредленным username
       //     System.out.println("Get user username = Antonette: \n"
        //            + httpUtil.sendGet(URI.create(String.format("%s?username=%s", GET_USER, "Delphine"))) + "\n");
//все комментарии к последнему посту определенного пользователя и записывать их в файл
        //    System.out.println("Get comments from last post: " + httpUtil.getCommentsFromLastPost(defaultUser) + "\n");
//будет выводить все открытые задачи для пользователя Х
            System.out.println(("Get open tasks: \n" + httpUtil.getOpenTask(defaultUser) + "\n"));

        }
    }










