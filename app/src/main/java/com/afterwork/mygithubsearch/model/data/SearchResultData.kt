package com.afterwork.mygithubsearch.model.data

/*
"total_count": 20708,
"items": [
  "id": 943149,
  "full_name": "d3/d3",
  "html_url": "https://github.com/d3/d3",
  "stargazers_count": 90222,
  "forks": 21854,
  "default_branch": "master",
  "open_issues_count": 4,
  "description": "Bring data to life with SVG, Canvas and HTML. :bar_chart::chart_with_upwards_trend::tada:",
  "owner": {
     "id": 1562726,
     "avatar_url": "https://avatars1.githubusercontent.com/u/1562726?v=4",
  }
}
 */

data class SearchResultData(val total_count: Int,
                            val items: List<SearchData>)