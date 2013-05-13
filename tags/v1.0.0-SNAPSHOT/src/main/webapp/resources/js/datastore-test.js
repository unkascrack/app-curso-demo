App.Store = DS.Store.extend({
	revision: 12,
	adapter: 'DS.FixtureAdapter'
});

App.Curso.FIXTURES = 
	[
		{
			"id":1,
			"activo":true,
			"tutor":1,
			"titulo":"Curso 1",
			"nivel":"BASICO",
			"horas":20,
			"temario":"temario1.txt"
		},
		{
			"id":2,
			"activo":true,
			"tutor":1,
			"titulo":"Curso 2",
			"nivel":"INTERMEDIO",
			"horas":40,
			"temario":"temario2.txt"
		},
		{
			"id":3,
			"activo":true,
			"tutor":1,
			"titulo":"Curso 3",
			"nivel":"INTERMEDIO",
			"horas":60,
			"temario":null
		},
		{
			"id":4,
			"activo":true,
			"tutor":[2],
			"titulo":"Curso 3",
			"nivel":"AVANZADO",
			"horas":120,
			"temario":null
		}
	];

	App.Tutor.FIXTURES = 
	[
		{
			"id":1,
			"nombre":"Carlos",
			"apellidos":"Alonso González"
		},
		{
			"id":2,
			"nombre":"Perico",
			"apellidos":"Palotes"
		}
	];