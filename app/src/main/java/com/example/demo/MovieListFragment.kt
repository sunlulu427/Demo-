package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.rekotlin.StoreSubscriber
import javax.security.auth.Subject

/**
 * @author: sunlulu.tomato@bytedance.com
 * @date:   2020/12/21 12:46 AM
 **/
class MovieListFragment: Fragment(), StoreSubscriber<MovieListState> {

    private lateinit var movieListAdapter: MovieListAdapter

    override fun newState(state: MovieListState) {
        state.movieObjects.let {
            initializeAdapter(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store.dispatch(LoadTop250MovieList())
    }

    private fun initializeAdapter() {
        val activity = this.activity as MainActivity
        movieListAdapter = MovieListAdapter(movieData, { id ->
            movieListToDetail(id, activity)
        })
        movieList.layoutManager = GridLayoutManager(context, 2)
        movieList.adapter = movieListAdapter
    }

    private fun movieListToDetail(subject: Subject, activity: MainActivity) {
       val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra("id", subject.id)
        intent.putExtra("title", subject.title)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) {
            it.select {
                it.movieListState
            }.skipRepeats()
        }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }
}