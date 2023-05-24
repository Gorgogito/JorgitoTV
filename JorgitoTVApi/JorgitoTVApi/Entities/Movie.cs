namespace JorgitoTVApi.Entities
{
  public class Movie 
  {
    public string? Id { get; set; }
    public string? Name { get; set; }
    public string? Title { get; set; }
    public string? BackgroundPath { get; set; }
    public string? PosterPath { get; set; }
    public string? TraillerPath { get; set; }
    public string? MoviePath { get; set; }
    public string? OverView { get; set; }
    public DateTime? ReleaseDate { get; set; }
    public List<GenreMovie> GenreMovies { get; set; }

  }
}
