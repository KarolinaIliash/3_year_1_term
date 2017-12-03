App for sending and receiving mails.
Most stuff is doing in class Menu.
It's ui form with action listeners on two buttons:
Send and See messages.
After user clicked on send message, we check whether
email and password is correct(catch exception in other case).
If user wants to send message, we send it to receiver email
with subject and content, entering by user.
If user wants to see all messages, we receive all messages 
from mail, create new ui form(Messages) and print messages' info
into this ui.