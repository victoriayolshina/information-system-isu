-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Июн 06 2021 г., 18:59
-- Версия сервера: 10.4.18-MariaDB
-- Версия PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `diploma`
--

-- --------------------------------------------------------

--
-- Структура таблицы `curator`
--

CREATE TABLE `curator` (
  `id` int(20) NOT NULL,
  `surname` varchar(225) NOT NULL,
  `name` varchar(225) NOT NULL,
  `patronymic` varchar(225) NOT NULL,
  `degree` varchar(225) NOT NULL,
  `email` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `curator`
--

INSERT INTO `curator` (`id`, `surname`, `name`, `patronymic`, `degree`, `email`) VALUES
(1, 'Рябец', 'Леонид ', 'Владимирович', 'доцент', 'riabets@gmail.com'),
(2, 'Семичева', 'Наталья', 'Леонидовна', 'доцент', 'leonidovna_natalia@gmail.com');

-- --------------------------------------------------------

--
-- Структура таблицы `deansemployee`
--

CREATE TABLE `deansemployee` (
  `id` int(11) NOT NULL,
  `surname` varchar(225) NOT NULL,
  `name` varchar(225) NOT NULL,
  `patronymic` varchar(225) NOT NULL,
  `post` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `deansemployee`
--

INSERT INTO `deansemployee` (`id`, `surname`, `name`, `patronymic`, `post`) VALUES
(1, 'Семичева', 'Наталья', 'Леонидовна', 'доцент');

-- --------------------------------------------------------

--
-- Структура таблицы `direction`
--

CREATE TABLE `direction` (
  `id` int(11) NOT NULL,
  `name` varchar(225) NOT NULL,
  `code` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `direction`
--

INSERT INTO `direction` (`id`, `name`, `code`) VALUES
(1, 'Прикладная Информатика', '09.03.03'),
(2, 'Фундаментальная Информатика', '03.03.03'),
(3, 'КРАСАВЧИК', '03.09.02');

-- --------------------------------------------------------

--
-- Структура таблицы `faculty`
--

CREATE TABLE `faculty` (
  `id` int(11) NOT NULL,
  `name` varchar(225) NOT NULL,
  `direction` int(225) NOT NULL,
  `year` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `faculty`
--

INSERT INTO `faculty` (`id`, `name`, `direction`, `year`) VALUES
(1, '2461', 1, 2017),
(2, '2471', 2, 2018),
(3, 'КРАСАВЧИКGROUP', 3, 2021);

-- --------------------------------------------------------

--
-- Структура таблицы `placeofpractice`
--

CREATE TABLE `placeofpractice` (
  `id` int(11) NOT NULL,
  `name` varchar(225) NOT NULL,
  `location` varchar(225) NOT NULL,
  `typeofdirection` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `placeofpractice`
--

INSERT INTO `placeofpractice` (`id`, `name`, `location`, `typeofdirection`) VALUES
(1, 'ZEON', 'Партизанская, 65', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `practice`
--

CREATE TABLE `practice` (
  `id` int(11) NOT NULL,
  `student` int(11) NOT NULL,
  `starttime` date NOT NULL,
  `endtime` date NOT NULL,
  `post` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `practice`
--

INSERT INTO `practice` (`id`, `student`, `starttime`, `endtime`, `post`) VALUES
(1, 1, '2021-02-07', '2021-03-20', 'Практикант');

-- --------------------------------------------------------

--
-- Структура таблицы `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `surname` varchar(225) NOT NULL,
  `name` varchar(225) NOT NULL,
  `patronymic` varchar(225) NOT NULL,
  `faculty` int(11) NOT NULL,
  `username` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `student`
--

INSERT INTO `student` (`id`, `surname`, `name`, `patronymic`, `faculty`, `username`) VALUES
(1, 'Ёлшина', 'Виктория', 'Евгеньевна', 1, 'yolshina2017'),
(2, 'Кислянников ', 'Марк', 'Александрович', 3, 'kislyannikov2018'),
(9, 'Иванов', 'Иван', 'Иванович', 3, 'ivanivanovich2014'),
(10, 'Ёлшина', 'Виктория', 'Евгеньевна', 1, 'victoria__yolshina');

-- --------------------------------------------------------

--
-- Структура таблицы `supervisior`
--

CREATE TABLE `supervisior` (
  `id` int(20) NOT NULL,
  `surname` varchar(225) NOT NULL,
  `name` varchar(225) NOT NULL,
  `patronymic` varchar(225) NOT NULL,
  `post` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `supervisior`
--

INSERT INTO `supervisior` (`id`, `surname`, `name`, `patronymic`, `post`) VALUES
(1, 'Журавлёв', 'Павел', 'Александрович', 'руководитель отдела внедрения и развития информационных систем');

-- --------------------------------------------------------

--
-- Структура таблицы `tasks`
--

CREATE TABLE `tasks` (
  `id` bigint(11) NOT NULL,
  `datastart` date NOT NULL,
  `dataend` date NOT NULL,
  `task` varchar(225) NOT NULL,
  `description` varchar(225) NOT NULL,
  `practice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tasks`
--

INSERT INTO `tasks` (`id`, `datastart`, `dataend`, `task`, `description`, `practice`) VALUES
(19, '2021-03-20', '2021-03-06', 'Изучение JAVA.', 'JAVAJAVA', 1),
(20, '2021-02-12', '2021-02-21', 'Заполнение дневника практики.', 'По инструкции ручками заполнили дневник.', 1),
(21, '2021-02-27', '2021-02-16', 'Изучение JAVA.', '', 1),
(22, '2021-02-13', '2021-02-09', 'Инструктаж по технике безопасности. Знакомство с организацией.', '', 1),
(24, '2021-03-18', '2021-03-19', 'Изучение JAVA.', 'JAVA', 1),
(25, '2021-02-07', '2021-02-11', 'Изучение Spring.', '', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `typeofdirection`
--

CREATE TABLE `typeofdirection` (
  `id` int(20) NOT NULL,
  `name` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `typeofdirection`
--

INSERT INTO `typeofdirection` (`id`, `name`) VALUES
(1, '1С'),
(2, 'Back-end');

-- --------------------------------------------------------

--
-- Структура таблицы `typeofpractice`
--

CREATE TABLE `typeofpractice` (
  `id` int(20) NOT NULL,
  `name` varchar(225) NOT NULL,
  `curator` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `typeofpractice`
--

INSERT INTO `typeofpractice` (`id`, `name`, `curator`) VALUES
(1, 'Учебная', 1),
(2, 'Преддипломная', 2);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `curator`
--
ALTER TABLE `curator`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `deansemployee`
--
ALTER TABLE `deansemployee`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `direction`
--
ALTER TABLE `direction`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `placeofpractice`
--
ALTER TABLE `placeofpractice`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `practice`
--
ALTER TABLE `practice`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `supervisior`
--
ALTER TABLE `supervisior`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `typeofdirection`
--
ALTER TABLE `typeofdirection`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `typeofpractice`
--
ALTER TABLE `typeofpractice`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `curator`
--
ALTER TABLE `curator`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `deansemployee`
--
ALTER TABLE `deansemployee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `direction`
--
ALTER TABLE `direction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `faculty`
--
ALTER TABLE `faculty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `placeofpractice`
--
ALTER TABLE `placeofpractice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `practice`
--
ALTER TABLE `practice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT для таблицы `supervisior`
--
ALTER TABLE `supervisior`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT для таблицы `typeofdirection`
--
ALTER TABLE `typeofdirection`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `typeofpractice`
--
ALTER TABLE `typeofpractice`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
