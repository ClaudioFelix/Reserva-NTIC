-- TipoRecurso: @codTipoRecurso, nomeTipoRecurso
-- StatusRecurso: @codStatRecurso, nomeStatRecurso
-- TipoUsuario: @codTipoUsuario, nomeTipoUsuario
-- StatusPedReserva: @codStatPedReserva, nomeStatPedReserva
-- Usuario: @idUsuario, login, senha, email, telContato, codTipoUsuario(FK)
-- Recurso: @idRecurso, nomeRecurso, descRecurso, codStatRecurso(FK), codTipoRecurso(FK)
-- PedidoReserva: @idRecurso(FK), @idUsuario(FK), idUtilizador(FK), codStatPedReserva(FK),
			   -- datPedReserva, horarioUtilRecurso, tempoUtilRecurso

-- TipoRecurso: @codTipoRecurso, nomeTipoRecurso
CREATE TABLE TipoRecurso(
	codTipoRecurso int not null ,
	nomeTipoRecurso varchar(100) not null,

	Constraint pk_TipoRecurso Primary Key(codTipoRecurso)
);

-- StatusRecurso: @codStatRecurso, nomeStatRecurso
CREATE TABLE StatusRecurso(
	codStatRecurso int not null,
	nomeStatRecurso varchar(50) not null,

	Constraint pk_StatRecurso Primary Key(codStatRecurso)
);

-- TipoUsuario: @codTipoUsuario, nomeTipoUsuario
CREATE TABLE TipoUsuario(
	codTipoUsuario int not null,
	nomeTipoUsuario varchar(50) not null,

	Constraint pk_TipoUsuario Primary Key(codTipoUsuario)
);

-- StatusPedReserva: @codStatPedReserva, nomeStatPedReserva
CREATE TABLE StatusPedReserva(
	codStatPedReserva int not null,
	nomeStatPedReserva varchar(50) not null,

	Constraint pk_StatPedReserva Primary Key(codStatPedReserva)
);

-- Usuario: @idUsuario, login, nome, senha, email, telContato, codTipoUsuario(FK)
CREATE TABLE Usuario(
	idUsuario int not null,
	login varchar(50) not null,
	nome varchar(100) not null,
	senha varchar(100) not null,
	email varchar(200) not null,
	telContato varchar(20) not null,
	codTipoUsuario int not null,

	Constraint pk_Usuario Primary Key(idUsuario),
	Constraint fk_Usuario_TipoUsuario Foreign Key(codTipoUsuario) references TipoUsuario(codTipoUsuario)
);

-- Recurso: @idRecurso, nomeRecurso, descRecurso, codStatRecurso(FK), codTipoRecurso(FK)
CREATE TABLE Recurso(
	idRecurso int not null,
	nomeRecurso varchar(100) not null,
	descRecurso varchar(256) null,
	codStatRecurso int not null,
	codTipoRecurso int not null,

	Constraint pk_Recurso Primary Key(idRecurso),
	Constraint fk_Recurso_StatusRecurso Foreign Key(codStatRecurso) references StatusRecurso(codStatRecurso),
	Constraint fk_Recurso_TipoRecurso Foreign Key(codTipoRecurso) references TipoRecurso(codTipoRecurso)
);

-- PedidoReserva: @idRecurso(FK), @idUsuario(FK), idUtilizador(FK), codStatPedReserva(FK),
			   -- datPedReserva, horarioUtilRecurso, tempoUtilRecurso
CREATE TABLE PedidoReserva(
	idRecurso int not null,
	idUsuario int not null,
	idUtilizador int not null,
	codStatPedReserva int not null,
	datPedReserva date not null,
	horarioUtilRecurso date not null,
	tempoUtilRecurso int not null,

	Constraint pk_PedidoReserva Primary Key(idRecurso, idUsuario),
	Constraint fk_PedidoReserva_Recurso Foreign Key(idRecurso) references Recurso(idRecurso),
	Constraint fk_PedidoReserva_Usuario Foreign Key(idUsuario) references Usuario(idUsuario),
	Constraint fk_PedidoReserva_UsuarioUtilizador Foreign Key(idUtilizador) references Usuario(idUsuario),
	Constraint fk_PedidoReserva_StatusPedReserva Foreign Key(codStatPedReserva) references StatusPedReserva(codStatPedReserva)
);