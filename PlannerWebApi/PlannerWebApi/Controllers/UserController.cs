using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Planner.Application.Account;
using Planner.Domain;
using Planner.EFData;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace PlannerWebApi.Controllers
{
    [Authorize]
    public class UserController : BaseController
    {
        private readonly DataContext _context;
        public UserController(DataContext context)
        {
            _context = context;
        }
        [HttpGet("profile")]
        public async Task<ActionResult<UserViewModel>> ProfileAsync()
        {
            var userName = User.Claims.FirstOrDefault(x => x.Type == "username").Value;
            var user = _context.Users.SingleOrDefault(x => x.UserName == userName);
            string imagePath = "/images/no_img.png";
            if (user.Image != "") 
            {
                imagePath = "/images/" + user.Image;
            }
            UserViewModel model = new UserViewModel()
            {
                UserName = userName,
                //Email = User.Claims.FirstOrDefault(x => x.Type == "username").Value,
                Image = imagePath;
            };
            return model;
        }
        [HttpPost("upload")]
        public async Task<IActionResult> UploadFile(IFormFile file)
        {
           var userName = User.Claims.FirstOrDefault(x => x.Type == "username").Value;
            var user = _context.Users.SingleOrDefault(x => x.UserName == userName);
          
            var fileName = Guid.NewGuid().ToString() +
                System.IO.Path.GetExtension(file.FileName);

            user.Image = fileName;
            _context.SaveChanges();
            var path = Path.Combine(
                          Directory.GetCurrentDirectory(), "uploads",
                          fileName);

            using (var stream = new FileStream(path, FileMode.Create))
            {
                await file.CopyToAsync(stream);
            }
            return Ok();
        }
    }
}
