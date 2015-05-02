// ==UserScript==
// @name        codility solved problem marker
// @namespace   codility
// @description mark solved problems
// @include     https://codility.com/programmers/lessons/*
// @include     https://codility.com/programmers/challenges/*
// @version     1
// @require     https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js
// @grant       GM_xmlhttpRequest
// ==/UserScript==

$(document).ready(function() {
  if (typeof Object.beget !== 'function') {
    Object.beget = function(o) {
      var F = function() {};
      F.prototype = o;
      return new F();
    };
  }

  var capitaliseFirstLetter = function(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
  };

  var baseProblemsMarker = {
    mark: function() {
      var self = this;

      $problemLinks = this.findProblemLinks();

      $problemLinks.each(function() {
        self.markOneProblem($(this));
      });
    },

    markOneProblem: function($problemLink) {
      var self = this;

      var name = this.extractName($problemLink);

      var url = 'https://raw.githubusercontent.com/charles-wangkai/codility/master/' + name + '.java';
      GM_xmlhttpRequest({
        method: 'GET',
        url: url,
        onload: function(resp) {
          if (resp.status === 200) {
            self.selectElementToMark($problemLink).css('background-color', 'lightgreen');
          }
        }
      });
    }
  };

  var problemsMarkerForLessons = Object.beget(baseProblemsMarker);
  problemsMarkerForLessons.findProblemLinks = function() {
    return $('a').filter(function() {
      return $(this).attr('href').indexOf('/demo/take-sample-test/') >= 0
          && !$(this).hasClass('prog-button');
    });
  };
  problemsMarkerForLessons.extractName = function($problemLink) {
    var capitaliseFirstLetter = function(str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    };

    return $.map($problemLink.attr('href').trim().substr('/demo/take-sample-test/'.length).split('_'), capitaliseFirstLetter).join('');
  };
  problemsMarkerForLessons.selectElementToMark = function($problemLink) {
    return $problemLink.parent().parent();
  };

  problemsMarkerForLessons.mark();


  var problemsMarkerForChallenges = Object.beget(baseProblemsMarker);
  problemsMarkerForChallenges.findProblemLinks = function() {
    return $('a').filter(function() {
      return $(this).attr('href').indexOf('/programmers/challenges/') >= 0;
    });
  };
  problemsMarkerForChallenges.extractName = function($problemLink) {
    var challenge2name = {
      'alpha2010': 'PrefixSet',
      'beta2010': 'NumberOfDiscIntersections',
      'gamma2011': 'CountPalindromicSlices',
      'delta2011': 'MinAbsSum',
      'epsilon2011': 'Minfuds',
      'zeta2011': 'BallSwitchBoard',
      'eta2011': 'HamiltonianRoutesCount',
      'theta2011': 'GasStations',
      'iota2011': 'ShortestAdjSeq',
      'kappa2011': 'SpaceCrews',
      'lambda2011': 'MinRouterPeripherality',
      'mu2011': 'NumberOfZeros',
      'nu2011': 'DoubleMedian',
    };

    return challenge2name[$problemLink.attr('href').trim().substr('/programmers/challenges/'.length)];
  };
  problemsMarkerForChallenges.selectElementToMark = function($problemLink) {
    return $problemLink.find('.title');
  };

  problemsMarkerForChallenges.mark();
});
