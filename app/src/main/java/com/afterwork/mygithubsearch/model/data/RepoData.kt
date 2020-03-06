package com.afterwork.mygithubsearch.model.data

/*
"id": 943149,
  "full_name": "d3/d3",
  "html_url": "https://github.com/d3/d3",
  "stargazers_count": 90222,
  "forks_count": 21854,
  "default_branch": "master",
  "open_issues_count": 4,
  "description": "Bring data to life with SVG, Canvas and HTML. :bar_chart::chart_with_upwards_trend::tada:",
  "owner": {
                "id": 1562726,
                "avatar_url": "https://avatars1.githubusercontent.com/u/1562726?v=4",
  }
 */

data class RepoData(val id: Int,
                      val full_name: String,
                      val html_url: String,
                      val stargazers_count: Int,
                      val forks_count: Int,
                      val default_branch: String,
                      val open_issues_count: Int,
                      val description: String,
                      val owner: OwnerData)