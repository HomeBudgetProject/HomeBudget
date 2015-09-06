module.exports = function(grunt) {

	grunt.initConfig({
		jshint: {
			all: ['js/**/*.js'],
			options: {
				globals: {
					_: false,
					$: false,
					jasmine: false,
					describe: false,
					it: false,
					beforeEach: false,
					afterEach: false,
					sinon: false
				},
				browser: true,
				devel: true
			}
		},
		sass: {
		    dist: {
		    	options: {
		    		style: 'compressed'
		    	},
		    	files: {
		    		'../static/css/style.css': 'scss/main.scss'
		    	}
		    }
		},
		concat: {
			dist: {
				src: ['bower_components/angular/angular.min.js', 'js/*js'],
				dest: '../static/js/app.js'
			}
		},
		sprite:{
			all : {
				src: ['img/sprite/*.png'],
				destImg: '../static/img/sprite.png',
				destCSS: 'scss/_sprite.scss',
				cssFormat: 'scss'
			}
		},
		copy: {
			main: {
				files: [
					{
						cwd: 'html/',
						src: '*',
						dest: '../static/',
						expand: true
					},
					{
						cwd: 'img/static/',
						src: ['*.png', '*.jpg'],
						dest: '../static/img/',
						expand: true
					}
				]
			}
		},
		concat_css: {
			all: {
				src: ['bower_components/bootstrap/dist/css/bootstrap.min.css', '../static/css/style.css'],
				dest: "../static/css/style.css"
			}
		}
	});

	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-sass');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-spritesmith');
	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-concat-css');

	grunt.registerTask('default', ['jshint', 'sprite', 'sass', 'concat', 'copy', 'concat_css']);

};