<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="nav-close"><i class="fa fa-times-circle"></i></div>
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <span>
                        <img alt="image" class="img-circle" src="${ctx.imageUrl}/upload/default.png"/>
                    </span>
                </div>
            </li>
        <@sidebar_menus>
            <#list sidebarMenus as menu>
                <li>
                    <a href="javascript:(0)">
                        <i class="fa ${menu.icon}"></i>
                        <span class="nav-label">${menu.name}</span>
                        <span class="fa arrow"></span>
                    </a>
                    <#if menu.children?size gt 0>
                        <ul class="nav nav-second-level">
                        <#list menu.children as child>
                            <#if child.children?size gt 0>
                                <li>
                                    <a href="#">
                                        ${child.name}
                                        <span class="fa arrow"></span>
                                    </a>
                                    <ul class="nav nav-third-level">
                                    <#list child.children as children>
                                        <li>
                                            <a class="J_menuItem" href="${children.link}">${children.name}</a>
                                        </li>
                                    </#list>
                                    </ul>
                                </li>
                            <#else>
                                <li>
                                    <a class="J_menuItem" href="${child.link}">${child.name}</a>
                                </li>
                            </#if>
                        </#list>
                        </ul>
                    </#if>
                </li>
            </#list>
        </@sidebar_menus>
        </ul>
    </div>
</nav>
<!--左侧导航结束-->