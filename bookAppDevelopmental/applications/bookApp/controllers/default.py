# -*- coding: utf-8 -*-
# this file is released under public domain and you can use without limitations

#########################################################################
## This is a sample controller
## - index is the default action of any application
## - user is required for authentication and authorization
## - download is for downloading files uploaded in the db (does streaming)
## - call exposes all registered services (none by default)
#########################################################################


def index():
    """
    example action using the internationalization operator T and flash
    rendered by views/default/index.html or views/generic.html

    if you need a simple wiki simple replace the two lines below with:
    return auth.wiki()
    """
    # grid = SQLFORM.grid(db.books, fields=[db.books.title],searchable=False, paginate=10, csv= False, deletable=False, editable=False, create=False)
    rows = db(db.books).select()
    form = SQLFORM(db.books)
    return dict(rows=rows, form=form)

def user():
    """
    exposes:
    http://..../[app]/default/user/login
    http://..../[app]/default/user/logout
    http://..../[app]/default/user/register
    http://..../[app]/default/user/profile
    http://..../[app]/default/user/retrieve_password
    http://..../[app]/default/user/change_password
    http://..../[app]/default/user/manage_users (requires membership in 
    use @auth.requires_login()
        @auth.requires_membership('group name')
        @auth.requires_permission('read','table name',record_id)
    to decorate functions that need access control
    """
    return dict(form=auth())

@cache.action()
def download():
    """
    allows downloading of uploaded files
    http://..../[app]/default/download/[filename]
    """
    return response.download(request, db)


def call():
    """
    exposes services. for example:
    http://..../[app]/default/call/jsonrpc
    decorate with @services.jsonrpc the functions to expose
    supports xml, json, xmlrpc, jsonrpc, amfrpc, rss, csv
    """
    return service()


@auth.requires_signature()
def data():
    """
    http://..../[app]/default/data/tables
    http://..../[app]/default/data/create/[table]
    http://..../[app]/default/data/read/[table]/[id]
    http://..../[app]/default/data/update/[table]/[id]
    http://..../[app]/default/data/delete/[table]/[id]
    http://..../[app]/default/data/select/[table]
    http://..../[app]/default/data/search/[table]
    but URLs must be signed, i.e. linked with
      A('table',_href=URL('data/tables',user_signature=True))
    or with the signed load operator
      LOAD('default','data.load',args='tables',ajax=True,user_signature=True)
    """
    return dict(form=crud())

def generate_data(): # populates the database with dummy data
    from gluon.contrib.populate import populate
    populate(db.books, 100)
    return "data generated"


def searchAjaxRequest():
    """
    saves the search made by the ajax request into a session variable
    so it can be accessed by ajaxTable() function later on
    """
    dataReceivedFromServer = request.body.read() # read json data from server
    from gluon.contrib import simplejson
    pythonDictionary = simplejson.loads(dataReceivedFromServer) # convert the data received into python dictionary and list format
    search = pythonDictionary[u'search']

    session.search = search
    return


def ajaxTable():
    query = db.books.title.like('%' + session.search + '%')
    rows = db(query).select()
    return dict(rows=rows)


def getBooks():
    """
    returns list of books as a json object to the android application
    """
    rows = db(db.books).select()
    return response.json(rows)


def insert():
    """
    inserts new book for both the web app and android app
    returns success message as a json object to the android application
    """
    dataReceivedFromServer = request.body.read() # read json data from server

    from gluon.contrib import simplejson
    pythonDictionary = simplejson.loads(dataReceivedFromServer) # convert the data received into python dictionary and list format

    bookTitle = pythonDictionary[u'bookTitle']
    bookEdition = pythonDictionary[u'bookEdition']
    bookCondition = pythonDictionary[u'bookCondition']
    email = pythonDictionary[u'email']

    db.books.insert(title=bookTitle, edition=bookEdition, bookCondition=bookCondition, email=email)
    session.booksPostedbyYou.append(bookTitle)
    print session.booksPostedbyYou
    return response.json("books inserted successfully")

def searchBooks():
    """
    returns a list of books matched as a json object to the android application
    """
    dataReceivedFromServer = request.body.read() # read json data from server

    from gluon.contrib import simplejson
    pythonDictionary = simplejson.loads(dataReceivedFromServer) # convert the data received into python dictionary and list format

    search = pythonDictionary[u'search']

    query = db.books.title.like('%' + search + '%')

    rows = db(query).select()
    return response.json(rows)





