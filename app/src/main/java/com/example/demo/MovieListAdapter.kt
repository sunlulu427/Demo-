package com.example.demo

import io.reactivex.rxjava3.subjects.Subject

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/21 12:56 AM
 **/
class MovieListAdapter(
    private val movieData: List<Subject>,
    private val imageClickCallback: (Subject) -> Unit
) : Recycl{
}