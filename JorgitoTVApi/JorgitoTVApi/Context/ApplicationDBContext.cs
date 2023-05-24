using JorgitoTVApi.Entities;
using Microsoft.EntityFrameworkCore;

namespace JorgitoTVApi.Context
{
  public class ApplicationDBContext : DbContext
  {
    public ApplicationDBContext(DbContextOptions<ApplicationDBContext> options) : base(options)
    {

    }

    public DbSet<Movie> Movie { get; set; }
    public DbSet<GenreMovie> GenreMovie { get; set; }

  }
}
