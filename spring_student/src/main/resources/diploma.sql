-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Май 20 2021 г., 04:53
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
-- Структура таблицы `faculty`
--

CREATE TABLE `faculty` (
  `id` int(11) NOT NULL,
  `name` varchar(225) NOT NULL,
  `direction` varchar(225) NOT NULL,
  `year` int(4) NOT NULL,
  `code` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `faculty`
--

INSERT INTO `faculty` (`id`, `name`, `direction`, `year`, `code`) VALUES
(1, 'Прикладная Информатика', 'ИИ', 2017, '09.03.03'),
(2, 'Фундаментальная Информатика', 'ИИ', 2018, '03.03.03');

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
(2, 'Кислянников ', 'Марк', 'Александрович', 2, 'kislyannikov2018');

-- --------------------------------------------------------

--
-- Структура таблицы `tasks`
--

CREATE TABLE `tasks` (
  `id` bigint(11) NOT NULL,
  `data` date NOT NULL,
  `task` varchar(225) NOT NULL,
  `description` varchar(225) NOT NULL,
  `practice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tasks`
--

INSERT INTO `tasks` (`id`, `data`, `task`, `description`, `practice`) VALUES
(1, '2021-02-24', 'Заполнение дневника практики.', 'По инструкции ручками заполнили дневник.', 1),
(2, '2021-05-15', 'ghhh', 'aaaa', 1),
(3, '2021-05-07', 'cfsadfvs', 'sd', 1),
(4, '2021-05-05', 'ghhh', 'bvrhjk rbknkfjdb fdbdjkfnbkjfdnb dfbdjfnbkjfb b erber brbgfbnjf bfkbjnf bjbnbnb fbnsk;b bsfbnjkfnbkjr fvdfbnj bvrhjk rbknkfjdb fdbdjkfnbkjfdnb dfbdjfnbkjfb b erber brbgfbnjf bfkbjnf bjbnbnb fbnsk;b vksdn dsjvsd fsfsfs fsdssdd', 1),
(5, '2021-05-13', '', 'aaaa', 1),
(6, '2021-04-23', 'ghhh', 'sd', 1),
(7, '2021-05-08', 'cfsadfvs', 'aaaa', 1),
(8, '2021-05-09', 'Изучение Spring', 'Изучение Spring', 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `faculty`
--
ALTER TABLE `faculty`
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
-- Индексы таблицы `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `faculty`
--
ALTER TABLE `faculty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `practice`
--
ALTER TABLE `practice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблицы `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
