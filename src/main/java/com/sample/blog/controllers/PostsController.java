package com.sample.blog.controllers;

import com.sample.blog.forms.PostForm;
import com.sample.blog.models.Post;
import com.sample.blog.models.User;
import com.sample.blog.services.NotificationService;
import com.sample.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostsController {
    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notifyService;

    @RequestMapping("posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Post post = postService.findById(id);

        if (post == null) {
            notifyService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "posts/view";
    }

    @RequestMapping("posts")
    public String viewPosts(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);

        return "posts/posts";
    }

    @RequestMapping("posts/create")
    public String createPost(PostForm postForm) {
        return "posts/create";
    }

    @RequestMapping(value="posts/create", method= RequestMethod.POST)
    public String createPost(@Valid PostForm postForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "posts/create";
        }

        notifyService.addInfoMessage("Successfully created a post!");
        return "redirect:/";
    }

    // == delete methods ==
    @RequestMapping("posts/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        Post post = postService.findById(id);
        if (post == null) {
            notifyService.addErrorMessage("There is no idea with the ide of " + id);
            return "redirect:/posts";
        }

        postService.deleteById(id);
        notifyService.addInfoMessage("You have successfully deleted [" + post.getTitle() + "]");

        return "redirect:/posts";
    }

    @RequestMapping("posts/update/{id}")
    public String updatePost(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        if (post == null) {
            notifyService.addErrorMessage("There is no post with id: " + id);
            return "redirect:/posts";
        }

        PostForm postForm = new PostForm();

        postForm.setTitle(post.getTitle());
        postForm.setBody(post.getBody());

        User author = post.getAuthor();
        if (author != null) {
            String fullName = author.getFullName();
            if (fullName != null && fullName != "") {
                postForm.setAuthor(fullName);
            } else {
                postForm.setAuthor(author.getUsername());
            }
        }

        model.addAttribute("postForm", postForm);
        return "posts/update";
    }

    // == update mehtods ==
    @RequestMapping(value="posts/update/{id}", method=RequestMethod.POST)
    public String updatePost(@Valid PostForm postForm, BindingResult bindingResult, @PathVariable Long id) {
        Post post = postService.findById(id);

        if (post == null) {
            notifyService.addErrorMessage("There are no posts with the id: "+ id + "!");
            return "redirect:/posts";
        }

        User author = new User();
        author.setFullName(postForm.getAuthor());

        post.setAuthor(author);
        post.setBody(postForm.getBody());
        post.setTitle(postForm.getTitle());


        postService.edit(post);
        notifyService.addInfoMessage("Successfully updated post!");

        return "redirect:/posts";
    }
}
