/*
 * bootstrap-table - v1.9.0 - 2015-09-30
 * https://github.com/wenzhixin/bootstrap-table
 * Copyright (c) 2015 zhixin wen
 * Licensed MIT License
 */
!function (a) {
    "use strict";
    var b = null, c = function (a) {
        var b = arguments, c = !0, d = 1;
        return a = a.replace(/%s/g, function () {
            var a = b[d++];
            return "undefined" == typeof a ? (c = !1, "") : a
        }), c ? a : ""
    }, d = function (b, c, d, e) {
        var f = "";
        return a.each(b, function (a, b) {
            return b[c] === e ? (f = b[d], !1) : !0
        }), f
    }, e = function (b, c) {
        var d = -1;
        return a.each(b, function (a, b) {
            return b.field === c ? (d = a, !1) : !0
        }), d
    }, f = function (b) {
        var c, d, e, f = 0, g = [];
        for (c = 0; c < b[0].length; c++)f += b[0][c].colspan || 1;
        for (c = 0; c < b.length; c++)for (g[c] = [], d = 0; f > d; d++)g[c][d] = !1;
        for (c = 0; c < b.length; c++)for (d = 0; d < b[c].length; d++) {
            var h = b[c][d], i = h.rowspan || 1, j = h.colspan || 1, k = a.inArray(!1, g[c]);
            for (1 === j && (h.fieldIndex = k, "undefined" == typeof h.field && (h.field = k)), e = 0; i > e; e++)g[c + e][k] = !0;
            for (e = 0; j > e; e++)g[c][k + e] = !0
        }
    }, g = function () {
        if (null === b) {
            var c, d, e = a("<p/>").addClass("fixed-table-scroll-inner"),
                f = a("<div/>").addClass("fixed-table-scroll-outer");
            f.append(e), a("body").append(f), c = e[0].offsetWidth, f.css("overflow", "scroll"), d = e[0].offsetWidth, c === d && (d = f[0].clientWidth), f.remove(), b = c - d
        }
        return b
    }, h = function (b, d, e, f) {
        var g = d;
        if ("string" == typeof d) {
            var h = d.split(".");
            h.length > 1 ? (g = window, a.each(h, function (a, b) {
                g = g[b]
            })) : g = window[d]
        }
        return "object" == typeof g ? g : "function" == typeof g ? g.apply(b, e) : !g && "string" == typeof d && c.apply(this, [d].concat(e)) ? c.apply(this, [d].concat(e)) : f
    }, i = function (b, c, d) {
        var e = Object.getOwnPropertyNames(b), f = Object.getOwnPropertyNames(c), g = "";
        if (d && e.length !== f.length)return !1;
        for (var h = 0; h < e.length; h++)if (g = e[h], a.inArray(g, f) > -1 && b[g] !== c[g])return !1;
        return !0
    }, j = function (a) {
        return "string" == typeof a ? a.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#039;") : a
    }, k = function (b) {
        var c = 0;
        return b.children().each(function () {
            c < a(this).outerHeight(!0) && (c = a(this).outerHeight(!0))
        }), c
    }, l = function (a) {
        for (var b in a) {
            var c = b.split(/(?=[A-Z])/).join("-").toLowerCase();
            c !== b && (a[c] = a[b], delete a[b])
        }
        return a
    }, m = function (a, b) {
        var c = a;
        if ("string" != typeof b || a.hasOwnProperty(b))return a[b];
        var d = b.split(".");
        for (var e in d)c = c[d[e]];
        return c
    }, n = function (b, c) {
        this.options = c, this.$el = a(b), this.$el_ = this.$el.clone(), this.timeoutId_ = 0, this.timeoutFooter_ = 0, this.init()
    };
    n.DEFAULTS = {
        classes: "table table-hover",
        locale: void 0,
        height: void 0,
        undefinedText: "-",
        sortName: void 0,
        sortOrder: "asc",
        striped: !1,
        columns: [[]],
        data: [],
        dataField: "rows",
        method: "get",
        url: void 0,
        ajax: void 0,
        cache: !0,
        contentType: "application/json",
        dataType: "json",
        ajaxOptions: {},
        queryParams: function (a) {
            return a
        },
        queryParamsType: "limit",
        responseHandler: function (a) {
            return a
        },
        pagination: !1,
        sidePagination: "client",
        totalRows: 0,
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 25, 50, 100],
        paginationHAlign: "right",
        paginationVAlign: "bottom",
        paginationDetailHAlign: "left",
        paginationFirstText: "&laquo;",
        paginationPreText: "&lsaquo;",
        paginationNextText: "&rsaquo;",
        paginationLastText: "&raquo;",
        search: !1,
        strictSearch: !1,
        searchAlign: "right",
        selectItemName: "btSelectItem",
        showHeader: !0,
        showFooter: !1,
        showColumns: !1,
        showPaginationSwitch: !1,
        showRefresh: !1,
        showToggle: !1,
        buttonsAlign: "right",
        smartDisplay: !0,
        minimumCountColumns: 1,
        idField: void 0,
        uniqueId: void 0,
        cardView: !1,
        detailView: !1,
        detailFormatter: function () {
            return ""
        },
        trimOnSearch: !0,
        clickToSelect: !1,
        singleSelect: !1,
        toolbar: void 0,
        toolbarAlign: "left",
        checkboxHeader: !0,
        sortable: !0,
        silentSort: !0,
        maintainSelected: !1,
        searchTimeOut: 500,
        searchText: "",
        iconSize: void 0,
        iconsPrefix: "glyphicon",
        icons: {
            paginationSwitchDown: "glyphicon-collapse-down icon-chevron-down",
            paginationSwitchUp: "glyphicon-collapse-up icon-chevron-up",
            refresh: "glyphicon-refresh icon-refresh",
            toggle: "glyphicon-list-alt icon-list-alt",
            columns: "glyphicon-th icon-th",
            detailOpen: "glyphicon-plus icon-plus",
            detailClose: "glyphicon-minus icon-minus"
        },
        rowStyle: function () {
            return {}
        },
        rowAttributes: function () {
            return {}
        },
        onAll: function () {
            return !1
        },
        onClickCell: function () {
            return !1
        },
        onDblClickCell: function () {
            return !1
        },
        onClickRow: function () {
            return !1
        },
        onDblClickRow: function () {
            return !1
        },
        onSort: function () {
            return !1
        },
        onCheck: function () {
            return !1
        },
        onUncheck: function () {
            return !1
        },
        onCheckAll: function () {
            return !1
        },
        onUncheckAll: function () {
            return !1
        },
        onCheckSome: function () {
            return !1
        },
        onUncheckSome: function () {
            return !1
        },
        onLoadSuccess: function () {
            return !1
        },
        onLoadError: function () {
            return !1
        },
        onColumnSwitch: function () {
            return !1
        },
        onPageChange: function () {
            return !1
        },
        onSearch: function () {
            return !1
        },
        onToggle: function () {
            return !1
        },
        onPreBody: function () {
            return !1
        },
        onPostBody: function () {
            return !1
        },
        onPostHeader: function () {
            return !1
        },
        onExpandRow: function () {
            return !1
        },
        onCollapseRow: function () {
            return !1
        },
        onRefreshOptions: function () {
            return !1
        },
        onResetView: function () {
            return !1
        }
    }, n.LOCALES = [], n.LOCALES["en-US"] = n.LOCALES.en = {
        formatLoadingMessage: function () {
            return "Loading, please wait..."
        }, formatRecordsPerPage: function (a) {
            return c("%s records per pagingAttribute", a)
        }, formatShowingRows: function (a, b, d) {
            return c("Showing %s to %s of %s rows", a, b, d)
        }, formatSearch: function () {
            return "Search"
        }, formatNoMatches: function () {
            return "No matching records found"
        }, formatPaginationSwitch: function () {
            return "Hide/Show pagination"
        }, formatRefresh: function () {
            return "Refresh"
        }, formatToggle: function () {
            return "Toggle"
        }, formatColumns: function () {
            return "Columns"
        }, formatAllRows: function () {
            return "All"
        }
    }, a.extend(n.DEFAULTS, n.LOCALES["en-US"]), n.COLUMN_DEFAULTS = {
        radio: !1,
        checkbox: !1,
        checkboxEnabled: !0,
        field: void 0,
        title: void 0,
        titleTooltip: void 0,
        "class": void 0,
        align: void 0,
        halign: void 0,
        falign: void 0,
        valign: void 0,
        width: void 0,
        sortable: !1,
        order: "asc",
        visible: !0,
        switchable: !0,
        clickToSelect: !0,
        formatter: void 0,
        footerFormatter: void 0,
        events: void 0,
        sorter: void 0,
        sortName: void 0,
        cellStyle: void 0,
        searchable: !0,
        searchFormatter: !0,
        cardVisible: !0
    }, n.EVENTS = {
        "all.bs.table": "onAll",
        "click-cell.bs.table": "onClickCell",
        "dbl-click-cell.bs.table": "onDblClickCell",
        "click-row.bs.table": "onClickRow",
        "dbl-click-row.bs.table": "onDblClickRow",
        "sort.bs.table": "onSort",
        "check.bs.table": "onCheck",
        "uncheck.bs.table": "onUncheck",
        "check-all.bs.table": "onCheckAll",
        "uncheck-all.bs.table": "onUncheckAll",
        "check-some.bs.table": "onCheckSome",
        "uncheck-some.bs.table": "onUncheckSome",
        "load-success.bs.table": "onLoadSuccess",
        "load-error.bs.table": "onLoadError",
        "column-switch.bs.table": "onColumnSwitch",
        "page-change.bs.table": "onPageChange",
        "search.bs.table": "onSearch",
        "toggle.bs.table": "onToggle",
        "pre-body.bs.table": "onPreBody",
        "post-body.bs.table": "onPostBody",
        "post-header.bs.table": "onPostHeader",
        "expand-row.bs.table": "onExpandRow",
        "collapse-row.bs.table": "onCollapseRow",
        "refresh-options.bs.table": "onRefreshOptions",
        "reset-view.bs.table": "onResetView"
    }, n.prototype.init = function () {
        this.initLocale(), this.initContainer(), this.initTable(), this.initHeader(), this.initData(), this.initFooter(), this.initToolbar(), this.initPagination(), this.initBody(), this.initSearchText(), this.initServer()
    }, n.prototype.initLocale = function () {
        if (this.options.locale) {
            var b = this.options.locale.split(/-|_/);
            b[0].toLowerCase(), b[1] && b[1].toUpperCase(), a.fn.bootstrapTable.locales[this.options.locale] ? a.extend(this.options, a.fn.bootstrapTable.locales[this.options.locale]) : a.fn.bootstrapTable.locales[b.join("-")] ? a.extend(this.options, a.fn.bootstrapTable.locales[b.join("-")]) : a.fn.bootstrapTable.locales[b[0]] && a.extend(this.options, a.fn.bootstrapTable.locales[b[0]])
        }
    }, n.prototype.initContainer = function () {
        this.$container = a(['<div class="bootstrap-table">', '<div class="fixed-table-toolbar"></div>', "top" === this.options.paginationVAlign || "both" === this.options.paginationVAlign ? '<div class="fixed-table-pagination" style="clear: both;"></div>' : "", '<div class="fixed-table-container">', '<div class="fixed-table-header"><table></table></div>', '<div class="fixed-table-body">', '<div class="fixed-table-loading">', this.options.formatLoadingMessage(), "</div>", "</div>", '<div class="fixed-table-footer"><table><tr></tr></table></div>', "bottom" === this.options.paginationVAlign || "both" === this.options.paginationVAlign ? '<div class="fixed-table-pagination"></div>' : "", "</div>", "</div>"].join("")), this.$container.insertAfter(this.$el), this.$tableContainer = this.$container.find(".fixed-table-container"), this.$tableHeader = this.$container.find(".fixed-table-header"), this.$tableBody = this.$container.find(".fixed-table-body"), this.$tableLoading = this.$container.find(".fixed-table-loading"), this.$tableFooter = this.$container.find(".fixed-table-footer"), this.$toolbar = this.$container.find(".fixed-table-toolbar"), this.$pagination = this.$container.find(".fixed-table-pagination"), this.$tableBody.append(this.$el), this.$container.after('<div class="clearfix"></div>'), this.$el.addClass(this.options.classes), this.options.striped && this.$el.addClass("table-striped"), -1 !== a.inArray("table-no-bordered", this.options.classes.split(" ")) && this.$tableContainer.addClass("table-no-bordered")
    }, n.prototype.initTable = function () {
        var b = this, c = [], d = [];
        this.$header = this.$el.find("thead"), this.$header.length || (this.$header = a("<thead></thead>").appendTo(this.$el)), this.$header.find("tr").each(function () {
            var b = [];
            a(this).find("th").each(function () {
                b.push(a.extend({}, {
                    title: a(this).html(),
                    "class": a(this).attr("class"),
                    titleTooltip: a(this).attr("title"),
                    rowspan: a(this).attr("rowspan") ? +a(this).attr("rowspan") : void 0,
                    colspan: a(this).attr("colspan") ? +a(this).attr("colspan") : void 0
                }, a(this).data()))
            }), c.push(b)
        }), a.isArray(this.options.columns[0]) || (this.options.columns = [this.options.columns]), this.options.columns = a.extend(!0, [], c, this.options.columns), this.columns = [], f(this.options.columns), a.each(this.options.columns, function (c, d) {
            a.each(d, function (d, e) {
                e = a.extend({}, n.COLUMN_DEFAULTS, e), "undefined" != typeof e.fieldIndex && (b.columns[e.fieldIndex] = e), b.options.columns[c][d] = e
            })
        }), this.options.data.length || (this.$el.find("tbody tr").each(function () {
            var c = {};
            c._id = a(this).attr("id"), c._class = a(this).attr("class"), c._data = l(a(this).data()), a(this).find("td").each(function (d) {
                var e = b.columns[d].field;
                c[e] = a(this).html(), c["_" + e + "_id"] = a(this).attr("id"), c["_" + e + "_class"] = a(this).attr("class"), c["_" + e + "_rowspan"] = a(this).attr("rowspan"), c["_" + e + "_title"] = a(this).attr("title"), c["_" + e + "_data"] = l(a(this).data())
            }), d.push(c)
        }), this.options.data = d)
    }, n.prototype.initHeader = function () {
        var b = this, d = {}, e = [];
        this.header = {
            fields: [],
            styles: [],
            classes: [],
            formatters: [],
            events: [],
            sorters: [],
            sortNames: [],
            cellStyles: [],
            searchables: []
        }, a.each(this.options.columns, function (f, g) {
            e.push("<tr>"), 0 == f && !b.options.cardView && b.options.detailView && e.push(c('<th class="detail" rowspan="%s"><div class="fht-cell"></div></th>', b.options.columns.length)), a.each(g, function (a, f) {
                var g = "", h = "", i = "", j = "", k = c(' class="%s"', f["class"]),
                    l = (b.options.sortOrder || f.order, "px"), m = f.width;
                if (void 0 === f.width || b.options.cardView || "string" == typeof f.width && -1 !== f.width.indexOf("%") && (l = "%"), f.width && "string" == typeof f.width && (m = f.width.replace("%", "").replace("px", "")), h = c("text-align: %s; ", f.halign ? f.halign : f.align), i = c("text-align: %s; ", f.align), j = c("vertical-align: %s; ", f.valign), j += c("width: %s; ", !f.checkbox && !f.radio || m ? m ? m + l : void 0 : "36px"), "undefined" != typeof f.fieldIndex) {
                    if (b.header.fields[f.fieldIndex] = f.field, b.header.styles[f.fieldIndex] = i + j, b.header.classes[f.fieldIndex] = k, b.header.formatters[f.fieldIndex] = f.formatter, b.header.events[f.fieldIndex] = f.events, b.header.sorters[f.fieldIndex] = f.sorter, b.header.sortNames[f.fieldIndex] = f.sortName, b.header.cellStyles[f.fieldIndex] = f.cellStyle, b.header.searchables[f.fieldIndex] = f.searchable, !f.visible)return;
                    if (b.options.cardView && !f.cardVisible)return;
                    d[f.field] = f
                }
                e.push("<th" + c(' title="%s"', f.titleTooltip), f.checkbox || f.radio ? c(' class="bs-checkbox %s"', f["class"] || "") : k, c(' style="%s"', h + j), c(' rowspan="%s"', f.rowspan), c(' colspan="%s"', f.colspan), c(' data-field="%s"', f.field), "tabindex='0'", ">"), e.push(c('<div class="th-inner %s">', b.options.sortable && f.sortable ? "sortable both" : "")), g = f.title, f.checkbox && (!b.options.singleSelect && b.options.checkboxHeader && (g = '<input name="btSelectAll" type="checkbox" />'), b.header.stateField = f.field), f.radio && (g = "", b.header.stateField = f.field, b.options.singleSelect = !0), e.push(g), e.push("</div>"), e.push('<div class="fht-cell"></div>'), e.push("</div>"), e.push("</th>")
            }), e.push("</tr>")
        }), this.$header.html(e.join("")), this.$header.find("th[data-field]").each(function () {
            a(this).data(d[a(this).data("field")])
        }), this.$container.off("click", ".th-inner").on("click", ".th-inner", function (c) {
            b.options.sortable && a(this).parent().data().sortable && b.onSort(c)
        }), this.$header.children().children().off("keypress").on("keypress", function (c) {
            if (b.options.sortable && a(this).data().sortable) {
                var d = c.keyCode || c.which;
                13 == d && b.onSort(c)
            }
        }), !this.options.showHeader || this.options.cardView ? (this.$header.hide(), this.$tableHeader.hide(), this.$tableLoading.css("top", 0)) : (this.$header.show(), this.$tableHeader.show(), this.$tableLoading.css("top", this.$header.outerHeight() + 1), this.getCaret()), this.$selectAll = this.$header.find('[name="btSelectAll"]'), this.$container.off("click", '[name="btSelectAll"]').on("click", '[name="btSelectAll"]', function () {
            var c = a(this).prop("checked");
            b[c ? "checkAll" : "uncheckAll"]()
        })
    }, n.prototype.initFooter = function () {
        !this.options.showFooter || this.options.cardView ? this.$tableFooter.hide() : this.$tableFooter.show()
    }, n.prototype.initData = function (a, b) {
        this.data = "append" === b ? this.data.concat(a) : "prepend" === b ? [].concat(a).concat(this.data) : a || this.options.data, this.options.data = "append" === b ? this.options.data.concat(a) : "prepend" === b ? [].concat(a).concat(this.options.data) : this.data, "server" !== this.options.sidePagination && this.initSort()
    }, n.prototype.initSort = function () {
        var b = this, c = this.options.sortName, d = "desc" === this.options.sortOrder ? -1 : 1,
            e = a.inArray(this.options.sortName, this.header.fields);
        -1 !== e && this.data.sort(function (f, g) {
            b.header.sortNames[e] && (c = b.header.sortNames[e]);
            var i = m(f, c), j = m(g, c), k = h(b.header, b.header.sorters[e], [i, j]);
            return void 0 !== k ? d * k : ((void 0 === i || null === i) && (i = ""), (void 0 === j || null === j) && (j = ""), a.isNumeric(i) && a.isNumeric(j) ? (i = parseFloat(i), j = parseFloat(j), j > i ? -1 * d : d) : i === j ? 0 : ("string" != typeof i && (i = i.toString()), -1 === i.localeCompare(j) ? -1 * d : d))
        })
    }, n.prototype.onSort = function (b) {
        var c = "keypress" === b.type ? a(b.currentTarget) : a(b.currentTarget).parent(),
            d = this.$header.find("th").eq(c.index());
        return this.$header.add(this.$header_).find("span.order").remove(), this.options.sortName === c.data("field") ? this.options.sortOrder = "asc" === this.options.sortOrder ? "desc" : "asc" : (this.options.sortName = c.data("field"), this.options.sortOrder = "asc" === c.data("order") ? "desc" : "asc"), this.trigger("sort", this.options.sortName, this.options.sortOrder), c.add(d).data("order", this.options.sortOrder), this.getCaret(), "server" === this.options.sidePagination ? void this.initServer(this.options.silentSort) : (this.initSort(), void this.initBody())
    }, n.prototype.initToolbar = function () {
        var b, d, f = this, g = [], i = 0, j = 0;
        this.$toolbar.html(""), ("string" == typeof this.options.toolbar || "object" == typeof this.options.toolbar) && a(c('<div class="bars pull-%s"></div>', this.options.toolbarAlign)).appendTo(this.$toolbar).append(a(this.options.toolbar)), g = [c('<div class="columns columns-%s btn-group pull-%s">', this.options.buttonsAlign, this.options.buttonsAlign)], "string" == typeof this.options.icons && (this.options.icons = h(null, this.options.icons)), this.options.showPaginationSwitch && g.push(c('<button class="btn btn-default" type="button" name="paginationSwitch" title="%s">', this.options.formatPaginationSwitch()), c('<i class="%s %s"></i>', this.options.iconsPrefix, this.options.icons.paginationSwitchDown), "</button>"), this.options.showRefresh && g.push(c('<button class="btn btn-default' + (void 0 === this.options.iconSize ? "" : " btn-" + this.options.iconSize) + '" type="button" name="refresh" title="%s">', this.options.formatRefresh()), c('<i class="%s %s"></i>', this.options.iconsPrefix, this.options.icons.refresh), "</button>"), this.options.showToggle && g.push(c('<button class="btn btn-default' + (void 0 === this.options.iconSize ? "" : " btn-" + this.options.iconSize) + '" type="button" name="toggle" title="%s">', this.options.formatToggle()), c('<i class="%s %s"></i>', this.options.iconsPrefix, this.options.icons.toggle), "</button>"), this.options.showColumns && (g.push(c('<div class="keep-open btn-group" title="%s">', this.options.formatColumns()), '<button type="button" class="btn btn-default' + (void 0 == this.options.iconSize ? "" : " btn-" + this.options.iconSize) + ' dropdown-toggle" data-toggle="dropdown">', c('<i class="%s %s"></i>', this.options.iconsPrefix, this.options.icons.columns), ' <span class="caret"></span>', "</button>", '<ul class="dropdown-menu" role="menu">'), a.each(this.columns, function (a, b) {
            if (!(b.radio || b.checkbox || f.options.cardView && !b.cardVisible)) {
                var d = b.visible ? ' checked="checked"' : "";
                b.switchable && (g.push(c('<li><label><input type="checkbox" data-field="%s" value="%s"%s> %s</label></li>', b.field, a, d, b.title)), j++)
            }
        }), g.push("</ul>", "</div>")), g.push("</div>"), (this.showToolbar || g.length > 2) && this.$toolbar.append(g.join("")), this.options.showPaginationSwitch && this.$toolbar.find('button[name="paginationSwitch"]').off("click").on("click", a.proxy(this.togglePagination, this)), this.options.showRefresh && this.$toolbar.find('button[name="refresh"]').off("click").on("click", a.proxy(this.refresh, this)), this.options.showToggle && this.$toolbar.find('button[name="toggle"]').off("click").on("click", function () {
            f.toggleView()
        }), this.options.showColumns && (b = this.$toolbar.find(".keep-open"), j <= this.options.minimumCountColumns && b.find("input").prop("disabled", !0), b.find("li").off("click").on("click", function (a) {
            a.stopImmediatePropagation()
        }), b.find("input").off("click").on("click", function () {
            var b = a(this);
            f.toggleColumn(e(f.columns, a(this).data("field")), b.prop("checked"), !1), f.trigger("column-switch", a(this).data("field"), b.prop("checked"))
        })), this.options.search && (g = [], g.push('<div class="pull-' + this.options.searchAlign + ' search">', c('<input class="form-control' + (void 0 === this.options.iconSize ? "" : " input-" + this.options.iconSize) + '" type="text" placeholder="%s">', this.options.formatSearch()), "</div>"), this.$toolbar.append(g.join("")), d = this.$toolbar.find(".search input"), d.off("keyup drop").on("keyup drop", function (a) {
            clearTimeout(i), i = setTimeout(function () {
                f.onSearch(a)
            }, f.options.searchTimeOut)
        }))
    }, n.prototype.onSearch = function (b) {
        var c = a.trim(a(b.currentTarget).val());
        this.options.trimOnSearch && a(b.currentTarget).val() !== c && a(b.currentTarget).val(c), c !== this.searchText && (this.searchText = c, this.options.pageNumber = 1, this.initSearch(), this.updatePagination(), this.trigger("search", c))
    }, n.prototype.initSearch = function () {
        var b = this;
        if ("server" !== this.options.sidePagination) {
            var c = this.searchText && this.searchText.toLowerCase(),
                d = a.isEmptyObject(this.filterColumns) ? null : this.filterColumns;
            this.data = d ? a.grep(this.options.data, function (a) {
                for (var b in d)if (a[b] !== d[b])return !1;
                return !0
            }) : this.options.data, this.data = c ? a.grep(this.data, function (d, f) {
                for (var g in d) {
                    g = a.isNumeric(g) ? parseInt(g, 10) : g;
                    var i = d[g], j = b.columns[e(b.columns, g)], k = a.inArray(g, b.header.fields);
                    j && j.searchFormatter && (i = h(j, b.header.formatters[k], [i, d, f], i));
                    var l = a.inArray(g, b.header.fields);
                    if (-1 !== l && b.header.searchables[l] && ("string" == typeof i || "number" == typeof i))if (b.options.strictSearch) {
                        if ((i + "").toLowerCase() === c)return !0
                    } else if (-1 !== (i + "").toLowerCase().indexOf(c))return !0
                }
                return !1
            }) : this.data
        }
    }, n.prototype.initPagination = function () {
        if (!this.options.pagination)return void this.$pagination.hide();
        this.$pagination.show();
        var b, d, e, f, g, h, i, j, k, l = this, m = [], n = !1, o = this.getData();
        if ("server" !== this.options.sidePagination && (this.options.totalRows = o.length), this.totalPages = 0, this.options.totalRows) {
            if (this.options.pageSize === this.options.formatAllRows()) this.options.pageSize = this.options.totalRows, n = !0; else if (this.options.pageSize === this.options.totalRows) {
                var p = "string" == typeof this.options.pageList ? this.options.pageList.replace("[", "").replace("]", "").replace(/ /g, "").toLowerCase().split(",") : this.options.pageList;
                p.indexOf(this.options.formatAllRows().toLowerCase()) > -1 && (n = !0)
            }
            this.totalPages = ~~((this.options.totalRows - 1) / this.options.pageSize) + 1, this.options.totalPages = this.totalPages
        }
        this.totalPages > 0 && this.options.pageNumber > this.totalPages && (this.options.pageNumber = this.totalPages), this.pageFrom = (this.options.pageNumber - 1) * this.options.pageSize + 1, this.pageTo = this.options.pageNumber * this.options.pageSize, this.pageTo > this.options.totalRows && (this.pageTo = this.options.totalRows), m.push('<div class="pull-' + this.options.paginationDetailHAlign + ' pagination-detail">', '<span class="pagination-info">', this.options.formatShowingRows(this.pageFrom, this.pageTo, this.options.totalRows), "</span>"), m.push('<span class="pagingAttribute-list">');
        var q = [c('<span class="btn-group %s">', "top" === this.options.paginationVAlign || "both" === this.options.paginationVAlign ? "dropdown" : "dropup"), '<button type="button" class="btn btn-default ' + (void 0 === this.options.iconSize ? "" : " btn-" + this.options.iconSize) + ' dropdown-toggle" data-toggle="dropdown">', '<span class="pagingAttribute-size">', n ? this.options.formatAllRows() : this.options.pageSize, "</span>", ' <span class="caret"></span>', "</button>", '<ul class="dropdown-menu" role="menu">'],
            r = this.options.pageList;
        if ("string" == typeof this.options.pageList) {
            var s = this.options.pageList.replace("[", "").replace("]", "").replace(/ /g, "").split(",");
            r = [], a.each(s, function (a, b) {
                r.push(b.toUpperCase() === l.options.formatAllRows().toUpperCase() ? l.options.formatAllRows() : +b)
            })
        }
        for (a.each(r, function (a, b) {
            if (!l.options.smartDisplay || 0 === a || r[a - 1] <= l.options.totalRows) {
                var d;
                d = n ? b === l.options.formatAllRows() ? ' class="active"' : "" : b === l.options.pageSize ? ' class="active"' : "", q.push(c('<li%s><a href="javascript:void(0)">%s</a></li>', d, b))
            }
        }), q.push("</ul></span>"), m.push(this.options.formatRecordsPerPage(q.join(""))), m.push("</span>"), m.push("</div>", '<div class="pull-' + this.options.paginationHAlign + ' pagination">', '<ul class="pagination' + (void 0 === this.options.iconSize ? "" : " pagination-" + this.options.iconSize) + '">', '<li class="pagingAttribute-first"><a href="javascript:void(0)">' + this.options.paginationFirstText + "</a></li>", '<li class="pagingAttribute-pre"><a href="javascript:void(0)">' + this.options.paginationPreText + "</a></li>"), this.totalPages < 5 ? (d = 1, e = this.totalPages) : (d = this.options.pageNumber - 2, e = d + 4, 1 > d && (d = 1, e = 5), e > this.totalPages && (e = this.totalPages, d = e - 4)), b = d; e >= b; b++)m.push('<li class="pagingAttribute-number' + (b === this.options.pageNumber ? " active" : "") + '">', '<a href="javascript:void(0)">', b, "</a>", "</li>");
        m.push('<li class="pagingAttribute-next"><a href="javascript:void(0)">' + this.options.paginationNextText + "</a></li>", '<li class="pagingAttribute-last"><a href="javascript:void(0)">' + this.options.paginationLastText + "</a></li>", "</ul>", "</div>"), this.$pagination.html(m.join("")), f = this.$pagination.find(".pagingAttribute-list a"), g = this.$pagination.find(".pagingAttribute-first"), h = this.$pagination.find(".pagingAttribute-pre"), i = this.$pagination.find(".pagingAttribute-next"), j = this.$pagination.find(".pagingAttribute-last"), k = this.$pagination.find(".page-number"), this.options.pageNumber <= 1 && (g.addClass("disabled"), h.addClass("disabled")), this.options.pageNumber >= this.totalPages && (i.addClass("disabled"), j.addClass("disabled")), this.options.smartDisplay && (this.totalPages <= 1 && this.$pagination.find("div.pagination").hide(), (r.length < 2 || this.options.totalRows <= r[0]) && this.$pagination.find("span.pagingAttribute-list").hide(), this.$pagination[this.getData().length ? "show" : "hide"]()), n && (this.options.pageSize = this.options.formatAllRows()), f.off("click").on("click", a.proxy(this.onPageListChange, this)), g.off("click").on("click", a.proxy(this.onPageFirst, this)), h.off("click").on("click", a.proxy(this.onPagePre, this)), i.off("click").on("click", a.proxy(this.onPageNext, this)), j.off("click").on("click", a.proxy(this.onPageLast, this)), k.off("click").on("click", a.proxy(this.onPageNumber, this))
    }, n.prototype.updatePagination = function (b) {
        b && a(b.currentTarget).hasClass("disabled") || (this.options.maintainSelected || this.resetRows(), this.initPagination(), "server" === this.options.sidePagination ? this.initServer() : this.initBody(), this.trigger("pagingAttribute-change", this.options.pageNumber, this.options.pageSize))
    }, n.prototype.onPageListChange = function (b) {
        var c = a(b.currentTarget);
        c.parent().addClass("active").siblings().removeClass("active"), this.options.pageSize = c.text().toUpperCase() === this.options.formatAllRows().toUpperCase() ? this.options.formatAllRows() : +c.text(), this.$toolbar.find(".pagingAttribute-size").text(this.options.pageSize), this.updatePagination(b)
    }, n.prototype.onPageFirst = function (a) {
        this.options.pageNumber = 1, this.updatePagination(a)
    }, n.prototype.onPagePre = function (a) {
        this.options.pageNumber--, this.updatePagination(a)
    }, n.prototype.onPageNext = function (a) {
        this.options.pageNumber++, this.updatePagination(a)
    }, n.prototype.onPageLast = function (a) {
        this.options.pageNumber = this.totalPages, this.updatePagination(a)
    }, n.prototype.onPageNumber = function (b) {
        this.options.pageNumber !== +a(b.currentTarget).text() && (this.options.pageNumber = +a(b.currentTarget).text(), this.updatePagination(b))
    }, n.prototype.initBody = function (b) {
        var f = this, g = [], i = this.getData();
        this.trigger("pre-body", i), this.$body = this.$el.find("tbody"), this.$body.length || (this.$body = a("<tbody></tbody>").appendTo(this.$el)), this.options.pagination && "server" !== this.options.sidePagination || (this.pageFrom = 1, this.pageTo = i.length);
        for (var k = this.pageFrom - 1; k < this.pageTo; k++) {
            var l, n = i[k], o = {}, p = [], q = "", r = {}, s = [];
            if (o = h(this.options, this.options.rowStyle, [n, k], o), o && o.css)for (l in o.css)p.push(l + ": " + o.css[l]);
            if (r = h(this.options, this.options.rowAttributes, [n, k], r))for (l in r)s.push(c('%s="%s"', l, j(r[l])));
            n._data && !a.isEmptyObject(n._data) && a.each(n._data, function (a, b) {
                "index" !== a && (q += c(' data-%s="%s"', a, b))
            }), g.push("<tr", c(" %s", s.join(" ")), c(' id="%s"', a.isArray(n) ? void 0 : n._id), c(' class="%s"', o.classes || (a.isArray(n) ? void 0 : n._class)), c(' data-index="%s"', k), c(' data-uniqueid="%s"', n[this.options.uniqueId]), c("%s", q), ">"), this.options.cardView && g.push(c('<td colspan="%s">', this.header.fields.length)), !this.options.cardView && this.options.detailView && g.push("<td>", '<a class="detail-icon" href="javascript:">', c('<i class="%s %s"></i>', this.options.iconsPrefix, this.options.icons.detailOpen), "</a>", "</td>"), a.each(this.header.fields, function (b, i) {
                var j = "", l = m(n, i), q = "", r = {}, s = "", t = f.header.classes[b], u = "", v = "", w = "",
                    x = f.columns[e(f.columns, i)];
                if (x.visible) {
                    if (o = c('style="%s"', p.concat(f.header.styles[b]).join("; ")), l = h(x, f.header.formatters[b], [l, n, k], l), n["_" + i + "_id"] && (s = c(' id="%s"', n["_" + i + "_id"])), n["_" + i + "_class"] && (t = c(' class="%s"', n["_" + i + "_class"])), n["_" + i + "_rowspan"] && (v = c(' rowspan="%s"', n["_" + i + "_rowspan"])), n["_" + i + "_title"] && (w = c(' title="%s"', n["_" + i + "_title"])), r = h(f.header, f.header.cellStyles[b], [l, n, k], r), r.classes && (t = c(' class="%s"', r.classes)), r.css) {
                        var y = [];
                        for (var z in r.css)y.push(z + ": " + r.css[z]);
                        o = c('style="%s"', y.concat(f.header.styles[b]).join("; "))
                    }
                    n["_" + i + "_data"] && !a.isEmptyObject(n["_" + i + "_data"]) && a.each(n["_" + i + "_data"], function (a, b) {
                        "index" !== a && (u += c(' data-%s="%s"', a, b))
                    }), x.checkbox || x.radio ? (q = x.checkbox ? "checkbox" : q, q = x.radio ? "radio" : q, j = [f.options.cardView ? '<div class="card-view">' : '<td class="bs-checkbox">', "<input" + c(' data-index="%s"', k) + c(' name="%s"', f.options.selectItemName) + c(' type="%s"', q) + c(' value="%s"', n[f.options.idField]) + c(' checked="%s"', l === !0 || l && l.checked ? "checked" : void 0) + c(' disabled="%s"', !x.checkboxEnabled || l && l.disabled ? "disabled" : void 0) + " />", f.header.formatters[b] && "string" == typeof l ? l : "", f.options.cardView ? "</div>" : "</td>"].join(""), n[f.header.stateField] = l === !0 || l && l.checked) : (l = "undefined" == typeof l || null === l ? f.options.undefinedText : l, j = f.options.cardView ? ['<div class="card-view">', f.options.showHeader ? c('<span class="title" %s>%s</span>', o, d(f.columns, "field", "title", i)) : "", c('<span class="value">%s</span>', l), "</div>"].join("") : [c("<td%s %s %s %s %s %s>", s, t, o, u, v, w), l, "</td>"].join(""), f.options.cardView && f.options.smartDisplay && "" === l && (j = "")), g.push(j)
                }
            }), this.options.cardView && g.push("</td>"), g.push("</tr>")
        }
        g.length || g.push('<tr class="no-records-found">', c('<td colspan="%s">%s</td>', this.$header.find("th").length, this.options.formatNoMatches()), "</tr>"), this.$body.html(g.join("")), b || this.scrollTo(0), this.$body.find("> tr[data-index] > td").off("click dblclick").on("click dblclick", function (b) {
            var d = a(this), g = d.parent(), h = f.data[g.data("index")], i = d[0].cellIndex,
                j = f.header.fields[f.options.detailView && !f.options.cardView ? i - 1 : i],
                k = f.columns[e(f.columns, j)], l = m(h, j);
            if (!d.find(".detail-icon").length && (f.trigger("click" === b.type ? "click-cell" : "dbl-click-cell", j, l, h, d), f.trigger("click" === b.type ? "click-row" : "dbl-click-row", h, g), "click" === b.type && f.options.clickToSelect && k.clickToSelect)) {
                var n = g.find(c('[name="%s"]', f.options.selectItemName));
                n.length && n[0].click()
            }
        }), this.$body.find("> tr[data-index] > td > .detail-icon").off("click").on("click", function () {
            var b = a(this), d = b.parent().parent(), e = d.data("index"), g = i[e];
            d.next().is("tr.detail-view") ? (b.find("i").attr("class", c("%s %s", f.options.iconsPrefix, f.options.icons.detailOpen)), d.next().remove(), f.trigger("collapse-row", e, g)) : (b.find("i").attr("class", c("%s %s", f.options.iconsPrefix, f.options.icons.detailClose)), d.after(c('<tr class="detail-view"><td colspan="%s">%s</td></tr>', d.find("td").length, h(f.options, f.options.detailFormatter, [e, g], ""))), f.trigger("expand-row", e, g, d.next().find("td"))), f.resetView()
        }), this.$selectItem = this.$body.find(c('[name="%s"]', this.options.selectItemName)), this.$selectItem.off("click").on("click", function (b) {
            b.stopImmediatePropagation();
            var c = a(this), d = c.prop("checked"), e = f.data[c.data("index")];
            f.options.maintainSelected && a(this).is(":radio") && a.each(f.options.data, function (a, b) {
                b[f.header.stateField] = !1
            }), e[f.header.stateField] = d, f.options.singleSelect && (f.$selectItem.not(this).each(function () {
                f.data[a(this).data("index")][f.header.stateField] = !1
            }), f.$selectItem.filter(":checked").not(this).prop("checked", !1)), f.updateSelected(), f.trigger(d ? "check" : "uncheck", e, c)
        }), a.each(this.header.events, function (b, c) {
            if (c) {
                "string" == typeof c && (c = h(null, c));
                var d = f.header.fields[b], e = a.inArray(d, f.getVisibleFields());
                f.options.detailView && !f.options.cardView && (e += 1);
                for (var g in c)f.$body.find("tr").each(function () {
                    var b = a(this), h = b.find(f.options.cardView ? ".card-view" : "td").eq(e), i = g.indexOf(" "),
                        j = g.substring(0, i), k = g.substring(i + 1), l = c[g];
                    h.find(k).off(j).on(j, function (a) {
                        var c = b.data("index"), e = f.data[c], g = e[d];
                        l.apply(this, [a, g, e, c])
                    })
                })
            }
        }), this.updateSelected(), this.resetView(), this.trigger("post-body")
    }, n.prototype.initServer = function (b, c) {
        var d, e = this, f = {}, g = {
            pageSize: this.options.pageSize === this.options.formatAllRows() ? this.options.totalRows : this.options.pageSize,
            pageNumber: this.options.pageNumber,
            searchText: this.searchText,
            sortName: this.options.sortName,
            sortOrder: this.options.sortOrder
        };
        (this.options.url || this.options.ajax) && ("limit" === this.options.queryParamsType && (g = {
            search: g.searchText,
            sort: g.sortName,
            order: g.sortOrder
        }, this.options.pagination && (g.limit = this.options.pageSize === this.options.formatAllRows() ? this.options.totalRows : this.options.pageSize, g.offset = this.options.pageSize === this.options.formatAllRows() ? 0 : this.options.pageSize * (this.options.pageNumber - 1))), a.isEmptyObject(this.filterColumnsPartial) || (g.filter = JSON.stringify(this.filterColumnsPartial, null)), f = h(this.options, this.options.queryParams, [g], f), a.extend(f, c || {}), f !== !1 && (b || this.$tableLoading.show(), d = a.extend({}, h(null, this.options.ajaxOptions), {
            type: this.options.method,
            url: this.options.url,
            data: "application/json" === this.options.contentType && "post" === this.options.method ? JSON.stringify(f) : f,
            cache: this.options.cache,
            contentType: this.options.contentType,
            dataType: this.options.dataType,
            success: function (a) {
                a = h(e.options, e.options.responseHandler, [a], a), e.load(a), e.trigger("load-success", a)
            },
            error: function (a) {
                e.trigger("load-error", a.status, a)
            },
            complete: function () {
                b || e.$tableLoading.hide()
            }
        }), this.options.ajax ? h(this, this.options.ajax, [d], null) : a.ajax(d)))
    }, n.prototype.initSearchText = function () {
        if (this.options.search && "" !== this.options.searchText) {
            var a = this.$toolbar.find(".search input");
            a.val(this.options.searchText), this.onSearch({
                currentTarget: a
            })
        }
    }, n.prototype.getCaret = function () {
        var b = this;
        a.each(this.$header.find("th"), function (c, d) {
            a(d).find(".sortable").removeClass("desc asc").addClass(a(d).data("field") === b.options.sortName ? b.options.sortOrder : "both")
        })
    }, n.prototype.updateSelected = function () {
        var b = this.$selectItem.filter(":enabled").length && this.$selectItem.filter(":enabled").length === this.$selectItem.filter(":enabled").filter(":checked").length;
        this.$selectAll.add(this.$selectAll_).prop("checked", b), this.$selectItem.each(function () {
            a(this).closest("tr")[a(this).prop("checked") ? "addClass" : "removeClass"]("selected")
        })
    }, n.prototype.updateRows = function () {
        var b = this;
        this.$selectItem.each(function () {
            b.data[a(this).data("index")][b.header.stateField] = a(this).prop("checked")
        })
    }, n.prototype.resetRows = function () {
        var b = this;
        a.each(this.data, function (a, c) {
            b.$selectAll.prop("checked", !1), b.$selectItem.prop("checked", !1), b.header.stateField && (c[b.header.stateField] = !1)
        })
    }, n.prototype.trigger = function (b) {
        var c = Array.prototype.slice.call(arguments, 1);
        b += ".bs.table", this.options[n.EVENTS[b]].apply(this.options, c), this.$el.trigger(a.Event(b), c), this.options.onAll(b, c), this.$el.trigger(a.Event("all.bs.table"), [b, c])
    }, n.prototype.resetHeader = function () {
        clearTimeout(this.timeoutId_), this.timeoutId_ = setTimeout(a.proxy(this.fitHeader, this), this.$el.is(":hidden") ? 100 : 0)
    }, n.prototype.fitHeader = function () {
        var b, d, e, f, h = this;
        if (h.$el.is(":hidden"))return void(h.timeoutId_ = setTimeout(a.proxy(h.fitHeader, h), 100));
        if (b = this.$tableBody.get(0), d = b.scrollWidth > b.clientWidth && b.scrollHeight > b.clientHeight + this.$header.outerHeight() ? g() : 0, this.$el.css("margin-top", -this.$header.outerHeight()), e = a(":focus"), e.length > 0) {
            var i = e.parents("th");
            if (i.length > 0) {
                var j = i.attr("data-field");
                if (void 0 !== j) {
                    var k = this.$header.find("[data-field='" + j + "']");
                    k.length > 0 && k.find(":input").addClass("focus-temp")
                }
            }
        }
        this.$header_ = this.$header.clone(!0, !0), this.$selectAll_ = this.$header_.find('[name="btSelectAll"]'), this.$tableHeader.css({"margin-right": d}).find("table").css("width", this.$el.outerWidth()).html("").attr("class", this.$el.attr("class")).append(this.$header_), f = a(".focus-temp:visible:eq(0)"), f.length > 0 && (f.focus(), this.$header.find(".focus-temp").removeClass("focus-temp")), this.$header.find("th[data-field]").each(function () {
            h.$header_.find(c('th[data-field="%s"]', a(this).data("field"))).data(a(this).data())
        });
        var l = this.getVisibleFields();
        this.$body.find("tr:first-child:not(.no-records-found) > *").each(function (b) {
            var d = a(this), e = b;
            h.options.detailView && !h.options.cardView && (0 === b && h.$header_.find("th.detail").find(".fht-cell").width(d.innerWidth()), e = b - 1), h.$header_.find(c('th[data-field="%s"]', l[e])).find(".fht-cell").width(d.innerWidth())
        }), this.$tableBody.off("scroll").on("scroll", function () {
            h.$tableHeader.scrollLeft(a(this).scrollLeft()), h.options.showFooter && !h.options.cardView && h.$tableFooter.scrollLeft(a(this).scrollLeft())
        }), h.trigger("post-header")
    }, n.prototype.resetFooter = function () {
        var b = this, d = b.getData(), e = [];
        this.options.showFooter && !this.options.cardView && (!this.options.cardView && this.options.detailView && e.push('<td><div class="th-inner">&nbsp;</div><div class="fht-cell"></div></td>'), a.each(this.columns, function (a, f) {
            var g = "", i = "", j = c(' class="%s"', f["class"]);
            f.visible && (!b.options.cardView || f.cardVisible) && (g = c("text-align: %s; ", f.falign ? f.falign : f.align), i = c("vertical-align: %s; ", f.valign), e.push("<td", j, c(' style="%s"', g + i), ">"), e.push('<div class="th-inner">'), e.push(h(f, f.footerFormatter, [d], "&nbsp;") || "&nbsp;"), e.push("</div>"), e.push('<div class="fht-cell"></div>'), e.push("</div>"), e.push("</td>"))
        }), this.$tableFooter.find("tr").html(e.join("")), clearTimeout(this.timeoutFooter_), this.timeoutFooter_ = setTimeout(a.proxy(this.fitFooter, this), this.$el.is(":hidden") ? 100 : 0))
    }, n.prototype.fitFooter = function () {
        var b, c, d;
        return clearTimeout(this.timeoutFooter_), this.$el.is(":hidden") ? void(this.timeoutFooter_ = setTimeout(a.proxy(this.fitFooter, this), 100)) : (c = this.$el.css("width"), d = c > this.$tableBody.width() ? g() : 0, this.$tableFooter.css({"margin-right": d}).find("table").css("width", c).attr("class", this.$el.attr("class")), b = this.$tableFooter.find("td"), void this.$body.find("tr:first-child:not(.no-records-found) > *").each(function (c) {
            var d = a(this);
            b.eq(c).find(".fht-cell").width(d.innerWidth())
        }))
    }, n.prototype.toggleColumn = function (a, b, d) {
        if (-1 !== a && (this.columns[a].visible = b, this.initHeader(), this.initSearch(), this.initPagination(), this.initBody(), this.options.showColumns)) {
            var e = this.$toolbar.find(".keep-open input").prop("disabled", !1);
            d && e.filter(c('[value="%s"]', a)).prop("checked", b), e.filter(":checked").length <= this.options.minimumCountColumns && e.filter(":checked").prop("disabled", !0)
        }
    }, n.prototype.toggleRow = function (a, b, d) {
        -1 !== a && this.$body.find("undefined" != typeof a ? c('tr[data-index="%s"]', a) : c('tr[data-uniqueid="%s"]', b))[d ? "show" : "hide"]()
    }, n.prototype.getVisibleFields = function () {
        var b = this, c = [];
        return a.each(this.header.fields, function (a, d) {
            var f = b.columns[e(b.columns, d)];
            f.visible && c.push(d)
        }), c
    }, n.prototype.resetView = function (a) {
        var b = 0;
        if (a && a.height && (this.options.height = a.height), this.$selectAll.prop("checked", this.$selectItem.length > 0 && this.$selectItem.length === this.$selectItem.filter(":checked").length), this.options.height) {
            var c = k(this.$toolbar), d = k(this.$pagination), e = this.options.height - c - d;
            this.$tableContainer.css("height", e + "px")
        }
        return this.options.cardView ? (this.$el.css("margin-top", "0"), void this.$tableContainer.css("padding-bottom", "0")) : (this.options.showHeader && this.options.height ? (this.$tableHeader.show(), this.resetHeader(), b += this.$header.outerHeight()) : (this.$tableHeader.hide(), this.trigger("post-header")), this.options.showFooter && (this.resetFooter(), this.options.height && (b += this.$tableFooter.outerHeight() + 1)), this.getCaret(), this.$tableContainer.css("padding-bottom", b + "px"), void this.trigger("reset-view"))
    }, n.prototype.getData = function (b) {
        return !this.searchText && a.isEmptyObject(this.filterColumns) && a.isEmptyObject(this.filterColumnsPartial) ? b ? this.options.data.slice(this.pageFrom - 1, this.pageTo) : this.options.data : b ? this.data.slice(this.pageFrom - 1, this.pageTo) : this.data
    }, n.prototype.load = function (b) {
        var c = !1;
        "server" === this.options.sidePagination ? (this.options.totalRows = b.total, c = b.fixedScroll, b = b[this.options.dataField]) : a.isArray(b) || (c = b.fixedScroll, b = b.data), this.initData(b), this.initSearch(), this.initPagination(), this.initBody(c)
    }, n.prototype.append = function (a) {
        this.initData(a, "append"), this.initSearch(), this.initPagination(), this.initBody(!0)
    }, n.prototype.prepend = function (a) {
        this.initData(a, "prepend"), this.initSearch(), this.initPagination(), this.initBody(!0)
    }, n.prototype.remove = function (b) {
        var c, d, e = this.options.data.length;
        if (b.hasOwnProperty("field") && b.hasOwnProperty("values")) {
            for (c = e - 1; c >= 0; c--)d = this.options.data[c], d.hasOwnProperty(b.field) && -1 !== a.inArray(d[b.field], b.values) && this.options.data.splice(c, 1);
            e !== this.options.data.length && (this.initSearch(), this.initPagination(), this.initBody(!0))
        }
    }, n.prototype.removeAll = function () {
        this.options.data.length > 0 && (this.options.data.splice(0, this.options.data.length), this.initSearch(), this.initPagination(), this.initBody(!0))
    }, n.prototype.getRowByUniqueId = function (a) {
        var b, c, d = this.options.uniqueId, e = this.options.data.length, f = null;
        for (b = e - 1; b >= 0; b--)if (c = this.options.data[b], c.hasOwnProperty(d) && ("string" == typeof c[d] ? a = a.toString() : "number" == typeof c[d] && (Number(c[d]) === c[d] && c[d] % 1 === 0 ? a = parseInt(a) : c[d] === Number(c[d]) && 0 !== c[d] && (a = parseFloat(a))), c[d] === a)) {
            f = c;
            break
        }
        return f
    }, n.prototype.removeByUniqueId = function (a) {
        var b = this.options.data.length, c = this.getRowByUniqueId(a);
        c && this.options.data.splice(this.options.data.indexOf(c), 1), b !== this.options.data.length && (this.initSearch(), this.initPagination(), this.initBody(!0))
    }, n.prototype.insertRow = function (a) {
        a.hasOwnProperty("index") && a.hasOwnProperty("row") && (this.data.splice(a.index, 0, a.row), this.initSearch(), this.initPagination(), this.initSort(), this.initBody(!0))
    }, n.prototype.updateRow = function (b) {
        b.hasOwnProperty("index") && b.hasOwnProperty("row") && (a.extend(this.data[b.index], b.row), this.initSort(), this.initBody(!0))
    }, n.prototype.showRow = function (a) {
        a.hasOwnProperty("index") && a.hasOwnProperty("uniqueId") && this.toggleRow(a.index, a.uniqueId, !0)
    }, n.prototype.hideRow = function (a) {
        a.hasOwnProperty("index") && a.hasOwnProperty("uniqueId") && this.toggleRow(a.index, a.uniqueId, !1)
    }, n.prototype.getRowsHidden = function (b) {
        var c = a(this.$body[0]).children().filter(":hidden"), d = 0;
        if (b)for (; d < c.length; d++)a(c[d]).show();
        return c
    }, n.prototype.mergeCells = function (b) {
        var c, d, e, f = b.index, g = a.inArray(b.field, this.getVisibleFields()), h = b.rowspan || 1,
            i = b.colspan || 1, j = this.$body.find("tr");
        if (this.options.detailView && !this.options.cardView && (g += 1), e = j.eq(f).find("td").eq(g), !(0 > f || 0 > g || f >= this.data.length)) {
            for (c = f; f + h > c; c++)for (d = g; g + i > d; d++)j.eq(c).find("td").eq(d).hide();
            e.attr("rowspan", h).attr("colspan", i).show()
        }
    }, n.prototype.updateCell = function (a) {
        a.hasOwnProperty("index") && a.hasOwnProperty("field") && a.hasOwnProperty("value") && (this.data[a.index][a.field] = a.value, this.initSort(), this.initBody(!0))
    }, n.prototype.getPage = function (params) {
        return {pageSize: this.options.pageSize, pageNumber: this.options.pageNumber};
    }, n.prototype.getOptions = function () {
        return this.options
    }, n.prototype.getSelections = function () {
        var b = this;
        return a.grep(this.data, function (a) {
            return a[b.header.stateField]
        })
    }, n.prototype.getAllSelections = function () {
        var b = this;
        return a.grep(this.options.data, function (a) {
            return a[b.header.stateField]
        })
    }, n.prototype.checkAll = function () {
        this.checkAll_(!0)
    }, n.prototype.uncheckAll = function () {
        this.checkAll_(!1)
    }, n.prototype.checkAll_ = function (a) {
        var b;
        a || (b = this.getSelections()), this.$selectAll.add(this.$selectAll_).prop("checked", a), this.$selectItem.filter(":enabled").prop("checked", a), this.updateRows(), a && (b = this.getSelections()), this.trigger(a ? "check-all" : "uncheck-all", b)
    }, n.prototype.check = function (a) {
        this.check_(!0, a)
    }, n.prototype.uncheck = function (a) {
        this.check_(!1, a)
    }, n.prototype.check_ = function (a, b) {
        this.$selectItem.filter(c('[data-index="%s"]', b)).prop("checked", a), this.data[b][this.header.stateField] = a, this.updateSelected(), this.trigger(a ? "check" : "uncheck", this.data[b])
    }, n.prototype.checkBy = function (a) {
        this.checkBy_(!0, a)
    }, n.prototype.uncheckBy = function (a) {
        this.checkBy_(!1, a)
    }, n.prototype.checkBy_ = function (b, d) {
        if (d.hasOwnProperty("field") && d.hasOwnProperty("values")) {
            var e = this, f = [];
            a.each(this.options.data, function (g, h) {
                return h.hasOwnProperty(d.field) ? void(-1 !== a.inArray(h[d.field], d.values) && (e.$selectItem.filter(":enabled").filter(c('[data-index="%s"]', g)).prop("checked", b), h[e.header.stateField] = b, f.push(h), e.trigger(b ? "check" : "uncheck", h))) : !1
            }), this.updateSelected(), this.trigger(b ? "check-some" : "uncheck-some", f)
        }
    }, n.prototype.destroy = function () {
        this.$el.insertBefore(this.$container), a(this.options.toolbar).insertBefore(this.$el), this.$container.next().remove(), this.$container.remove(), this.$el.html(this.$el_.html()).css("margin-top", "0").attr("class", this.$el_.attr("class") || "")
    }, n.prototype.showLoading = function () {
        this.$tableLoading.show()
    }, n.prototype.hideLoading = function () {
        this.$tableLoading.hide()
    }, n.prototype.togglePagination = function () {
        this.options.pagination = !this.options.pagination;
        var a = this.$toolbar.find('button[name="paginationSwitch"] i');
        this.options.pagination ? a.attr("class", this.options.iconsPrefix + " " + this.options.icons.paginationSwitchDown) : a.attr("class", this.options.iconsPrefix + " " + this.options.icons.paginationSwitchUp), this.updatePagination()
    }, n.prototype.refresh = function (a) {
        a && a.url && (this.options.url = a.url, this.options.pageNumber = 1), this.initServer(a && a.silent, a && a.query)
    }, n.prototype.resetWidth = function () {
        this.options.showHeader && this.options.height && this.fitHeader(), this.options.showFooter && this.fitFooter()
    }, n.prototype.showColumn = function (a) {
        this.toggleColumn(e(this.columns, a), !0, !0)
    }, n.prototype.hideColumn = function (a) {
        this.toggleColumn(e(this.columns, a), !1, !0)
    }, n.prototype.getHiddenColumns = function () {
        return a.grep(this.columns, function (a) {
            return !a.visible
        })
    }, n.prototype.filterBy = function (b) {
        this.filterColumns = a.isEmptyObject(b) ? {} : b, this.options.pageNumber = 1, this.initSearch(), this.updatePagination()
    }, n.prototype.scrollTo = function (a) {
        return "string" == typeof a && (a = "bottom" === a ? this.$tableBody[0].scrollHeight : 0), "number" == typeof a && this.$tableBody.scrollTop(a), "undefined" == typeof a ? this.$tableBody.scrollTop() : void 0
    }, n.prototype.getScrollPosition = function () {
        return this.scrollTo()
    }, n.prototype.selectPage = function (a) {
        a > 0 && a <= this.options.totalPages && (this.options.pageNumber = a, this.updatePagination())
    }, n.prototype.prevPage = function () {
        this.options.pageNumber > 1 && (this.options.pageNumber--, this.updatePagination())
    }, n.prototype.nextPage = function () {
        this.options.pageNumber < this.options.totalPages && (this.options.pageNumber++, this.updatePagination())
    }, n.prototype.toggleView = function () {
        this.options.cardView = !this.options.cardView, this.initHeader(), this.initBody(), this.trigger("toggle", this.options.cardView)
    }, n.prototype.refreshOptions = function (b) {
        i(this.options, b, !1) || (this.options = a.extend(this.options, b), this.trigger("refresh-options", this.options), this.destroy(), this.init())
    }, n.prototype.resetSearch = function (a) {
        var b = this.$toolbar.find(".search input");
        b.val(a || ""), this.onSearch({currentTarget: b})
    }, n.prototype.expandRow_ = function (a, b) {
        var d = this.$body.find(c('> tr[data-index="%s"]', b));
        d.next().is("tr.detail-view") === (a ? !1 : !0) && d.find("> td > .detail-icon").click()
    }, n.prototype.expandRow = function (a) {
        this.expandRow_(!0, a)
    }, n.prototype.collapseRow = function (a) {
        this.expandRow_(!1, a)
    }, n.prototype.expandAllRows = function (b) {
        if (b) {
            var d = this.$body.find(c('> tr[data-index="%s"]', 0)), e = this, f = null, g = !1, h = -1;
            if (d.next().is("tr.detail-view") ? d.next().next().is("tr.detail-view") || (d.next().find(".detail-icon").click(), g = !0) : (d.find("> td > .detail-icon").click(), g = !0), g)try {
                h = setInterval(function () {
                    f = e.$body.find("tr.detail-view").last().find(".detail-icon"), f.length > 0 ? f.click() : clearInterval(h)
                }, 1)
            } catch (i) {
                clearInterval(h)
            }
        } else for (var j = this.$body.children(), k = 0; k < j.length; k++)this.expandRow_(!0, a(j[k]).data("index"))
    }, n.prototype.collapseAllRows = function (b) {
        if (b) this.expandRow_(!1, 0); else for (var c = this.$body.children(), d = 0; d < c.length; d++)this.expandRow_(!1, a(c[d]).data("index"))
    };
    var o = ["getOptions", "getSelections", "getAllSelections", "getData", "load", "append", "prepend", "remove", "removeAll", "insertRow", "updateRow", "updateCell", "removeByUniqueId", "getRowByUniqueId", "showRow", "hideRow", "getRowsHidden", "mergeCells", "checkAll", "uncheckAll", "check", "uncheck", "checkBy", "uncheckBy", "refresh", "resetView", "resetWidth", "destroy", "showLoading", "hideLoading", "showColumn", "hideColumn", "getHiddenColumns", "filterBy", "scrollTo", "getScrollPosition", "selectPage", "prevPage", "nextPage", "togglePagination", "toggleView", "refreshOptions", "resetSearch", "expandRow", "collapseRow", "expandAllRows", "collapseAllRows", 'getPage'];
    a.fn.bootstrapTable = function (b) {
        var c, d = Array.prototype.slice.call(arguments, 1);
        return this.each(function () {
            var e = a(this), f = e.data("bootstrap.table"),
                g = a.extend({}, n.DEFAULTS, e.data(), "object" == typeof b && b);
            if ("string" == typeof b) {
                if (a.inArray(b, o) < 0)throw new Error("Unknown method: " + b);
                if (!f)return;
                c = f[b].apply(f, d), "destroy" === b && e.removeData("bootstrap.table")
            }
            f || e.data("bootstrap.table", f = new n(this, g))
        }), "undefined" == typeof c ? this : c
    }, a.fn.bootstrapTable.Constructor = n, a.fn.bootstrapTable.defaults = n.DEFAULTS, a.fn.bootstrapTable.columnDefaults = n.COLUMN_DEFAULTS, a.fn.bootstrapTable.locales = n.LOCALES, a.fn.bootstrapTable.methods = o, a.fn.bootstrapTable.utils = {
        sprintf: c,
        getFieldIndex: e,
        compareObjects: i,
        calculateObjectValue: h
    }, a(function () {
        a('[data-toggle="table"]').bootstrapTable()
    })
}(jQuery);
