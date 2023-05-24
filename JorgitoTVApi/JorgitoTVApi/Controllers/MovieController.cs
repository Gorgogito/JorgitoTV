using JorgitoTVApi.Context;
using JorgitoTVApi.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Conventions;
using System.Net;

namespace JorgitoTVApi.Controllers
{

  [ApiController]
  [Route("Api/v1/[controller]")]
  public class MovieController : ControllerBase
  {

    private readonly ApplicationDBContext context;

    public MovieController(ApplicationDBContext context)
    { this.context = context; }

    //[HttpGet()]
    //public ActionResult<JSon> GetMovieAll()
    //{
    //  var movie = context.Movie.Include(x => x.GenreMovies).ToList();
    //  if (movie == null)
    //  { return NotFound(); }

    //  JSon json = new JSon
    //  {
    //    page = 1,
    //    results = movie
    //  };

    //  return json;
    //}

    //[HttpGet("{filter_genre}", Name = "Genre","{order_date}", Name = "sortdate")]
    [HttpGet]
    public ActionResult<JSon> GetMovieAllWithParams([FromQuery] long filter_genre, [FromQuery] string order_date)
    {
      object movie = null;
      //var movie = context.Movie.ToList();
      if (order_date == "asc")
      {
        movie = (from m in context.Movie
                 orderby m.ReleaseDate ascending
                 select new
                 {
                   m.Id,
                   m.Name,
                   m.Title,
                   m.BackgroundPath,
                   m.PosterPath,
                   m.TraillerPath,
                   m.MoviePath,
                   m.OverView,
                   m.ReleaseDate,
                   genreMovies = (from g in context.GenreMovie where g.MovieId == m.Id select g.GenreId).ToList()
                 }
                 ).ToList().Where(w => w.genreMovies.Contains(filter_genre));
      }
      if (order_date == "desc")
      {
        movie = (from m in context.Movie
                 orderby m.ReleaseDate descending
                 select new
                 {
                   m.Id,
                   m.Name,
                   m.Title,
                   m.BackgroundPath,
                   m.PosterPath,
                   m.TraillerPath,
                   m.MoviePath,
                   m.OverView,
                   m.ReleaseDate,
                   genreMovies = (from g in context.GenreMovie where g.MovieId == m.Id select g.GenreId).ToList()
                 }
                   ).ToList().Where(w => w.genreMovies.Contains(filter_genre));
      }

      if (movie == null)
      { return NotFound(); }

      JSon json = new JSon
      {
        page = 1,
        results = movie
      };

      return json;
    }

    [HttpGet("{id}", Name = "Code")]
    public ActionResult<JSon> GetMovieByCode(string id)
    {
      var movie = context.Movie.Include(x => x.GenreMovies).Where(x => x.Id == id).ToList();
      if (movie == null)
      { return NotFound(); }
      JSon json = new JSon
      {
        page = 1,
        results = movie
      };

      return json;
    }

  }
}
