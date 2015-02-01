// ==UserScript==
// @name        codility solved problem marker
// @namespace   codility
// @description mark solved problems
// @include     https://codility.com/programmers/lessons/*
// @version     1
// @require     https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js
// @grant       GM_xmlhttpRequest
// ==/UserScript==

$(document).ready(function() {
  var RENAMED_PROBLEMS = {
  };

  var capitaliseFirstLetter = function(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
  };
  
  var markSolvedProblem = function($problemName) {
    var name = $.map($problemName.attr('href').trim().substr('/demo/take-sample-test/'.length).split('_'), capitaliseFirstLetter).join('');
    if (name in RENAMED_PROBLEMS) {
      name = RENAMED_PROBLEMS[name];
    }
    var url = 'https://raw.githubusercontent.com/charles-wangkai/codility/master/' + name + '.java';
    GM_xmlhttpRequest({
      method: 'GET',
      url: url,
      onload: function(resp) {
        if (resp.status === 200) {
          $problemName.parent().parent().css('background-color', 'lightgreen');
        }
      }
    });
  };
  
  var $problemNames = $('a').filter(function() {
    return $(this).attr('href').indexOf('/demo/take-sample-test/') >= 0
        && !$(this).hasClass('prog-button');
  });

  $.each($problemNames, function(index, value) {
    markSolvedProblem($(value));
  });
});
